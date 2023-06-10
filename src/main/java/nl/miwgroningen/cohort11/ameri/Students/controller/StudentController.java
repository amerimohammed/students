package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Student;
import nl.miwgroningen.cohort11.ameri.Students.repository.CohortRepository;
import nl.miwgroningen.cohort11.ameri.Students.repository.StudentRepository;
import nl.miwgroningen.cohort11.ameri.Students.service.EmailService;
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
    private final EmailService emailService;

    @PostMapping("")
    private String saveOrUpdateStudent(@ModelAttribute Student student, BindingResult result) {
        if (!result.hasErrors()) {
            if (student.getUserId() == null) {
                student.generateUsernameAndPassword();
                String tempPassword = student.getPassword();
                student.hashPassword();
                studentRepository.save(student);
                emailService.sendSimpleMessage("noreply@academy.nl", student.getEmail(),
                        "Account created",
                        String.format("Your account has been created.\nYou can log in now using:\n" +
                                        "Username: %s\nPassword: %s\nYou can change your password using profile page."
                                , student.getUsername(), tempPassword
                        ));
            } else {
                Optional<Student> storedStudent = studentRepository.findById(student.getUserId());
                if (storedStudent.isPresent()) {
                    student.setUsername(storedStudent.get().getUsername());
                    student.setPassword(storedStudent.get().getPassword());
                    studentRepository.save(student);
                }
            }
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
