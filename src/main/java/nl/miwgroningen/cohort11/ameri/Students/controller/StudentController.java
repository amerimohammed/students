package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Student;
import nl.miwgroningen.cohort11.ameri.Students.repository.CohortRepository;
import nl.miwgroningen.cohort11.ameri.Students.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * handles user interaction with student pages
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final CohortRepository cohortRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    private String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("")
    private String saveOrUpdateStudent(@ModelAttribute Student student, BindingResult result) {
        if (!result.hasErrors()) {
            student.setUsername("malameri");
            student.setPassword(passwordEncoder.encode("test"));
            studentRepository.save(student);
        }
        return "redirect:/student";
    }

    @GetMapping({"", "/overview"})
    private String showStudentsOverview(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        model.addAttribute("cohorts", cohortRepository.findAll());
        return "/studentOverview";
    }

    @GetMapping("/delete/{userId}")
    private String deleteCohort(@PathVariable("userId") Long userId, Model model) {
        Optional<Student> optionalStudent = studentRepository.findById(userId);

        if (optionalStudent.isPresent()) {
            try {
                studentRepository.delete(optionalStudent.get());
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                System.out.println(dataIntegrityViolationException.getMessage());
                model.addAttribute("errorMessage",
                        "This Student can't be deleted due to relation to other entities");
                return "error";
            }
        }
        return "redirect:/student";
    }
}
