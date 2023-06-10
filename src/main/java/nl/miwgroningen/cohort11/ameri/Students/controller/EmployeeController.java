package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Employee;
import nl.miwgroningen.cohort11.ameri.Students.repository.CohortRepository;
import nl.miwgroningen.cohort11.ameri.Students.repository.EmployeeRepository;
import nl.miwgroningen.cohort11.ameri.Students.service.EmailService;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CohortRepository cohortRepository;
    private final EmailService emailService;

    @PostMapping("")
    private String saveOrUpdateEmployee(@ModelAttribute Employee employee, BindingResult result) {
        if (!result.hasErrors()) {
            if (employee.getUserId() == null) {
                employee.generateUsernameAndPassword();
                String tempPassword = employee.getPassword();
                employee.hashPassword();
                employeeRepository.save(employee);
                emailService.sendSimpleMessage("noreply@academy.nl", employee.getEmail(),
                        "Account created",
                        String.format("Your account has been created.\nYou can log in now using:\n" +
                                        "Username: %s\nPassword: %s\nYou can change your password using profile page."
                                , employee.getUsername(), tempPassword
                        ));
            } else {
                Optional<Employee> storedEmployee = employeeRepository.findById(employee.getUserId());
                if (storedEmployee.isPresent()) {
                    employee.setUsername(storedEmployee.get().getUsername());
                    employee.setPassword(storedEmployee.get().getPassword());
                    employeeRepository.save(employee);
                }
            }
        }
        return "redirect:/employee";
    }

    @GetMapping({"", "/overview"})
    private String showEmployeesOverview(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("cohorts", cohortRepository.findAll());
        return "employeeOverview";
    }

    @GetMapping("/delete/{userId}")
    private String deleteCohort(@PathVariable("userId") Long userId, Model model) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);

        if (optionalEmployee.isPresent()) {
            try {
                employeeRepository.delete(optionalEmployee.get());
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                System.out.println(dataIntegrityViolationException.getMessage());
                model.addAttribute("errorMessage",
                        "This Employee can't be deleted due to relation to other entities");
                return "error";
            }
        }
        return "redirect:/employee";
    }
}
