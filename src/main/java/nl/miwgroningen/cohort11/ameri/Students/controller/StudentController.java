package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Student;
import nl.miwgroningen.cohort11.ameri.Students.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project handles user interaction with student pages
 */
@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping("/student/new")
    private String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("/student")
    private String saveOrUpdateStudent(@ModelAttribute Student student, BindingResult result) {
        if (!result.hasErrors()) {
            studentRepository.save(student);
        }
        return "redirect:/";
    }
}
