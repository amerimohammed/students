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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project handles user interaction with student pages
 */
@RequestMapping("/student")
@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping("/new")
    private String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("/")
    private String saveOrUpdateStudent(@ModelAttribute Student student, BindingResult result) {
        if (!result.hasErrors()) {
            studentRepository.save(student);
        }
        return "redirect:/";
    }

    @GetMapping({"/", "/overview"})
    private String showStudentsOverview(Model model){
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "studentOverview";
    }
}
