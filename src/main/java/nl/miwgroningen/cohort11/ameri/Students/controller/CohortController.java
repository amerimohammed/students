package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Cohort;
import nl.miwgroningen.cohort11.ameri.Students.repository.CohortRepository;
import nl.miwgroningen.cohort11.ameri.Students.repository.CourseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project Manages all user requests and interactions with Cohort pages
 */
@RequestMapping("/cohort")
@Controller
@RequiredArgsConstructor
public class CohortController {
    private final CohortRepository cohortRepository;
    private final CourseRepository courseRepository;

    @GetMapping({"", "/overview"})
    private String showCohortOverview(Model model) {
        model.addAttribute("cohorts", cohortRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("cohort", new Cohort());
        return "cohortOverview";
    }

    @GetMapping("/new")
    private String ShowCohortNewEditForm(Model model) {
        model.addAttribute("cohort", new Cohort());
        return "cohortNewEditForm";
    }

    @PostMapping("")
    private String SaveUpdateCohort(@ModelAttribute("cohort") Cohort cohort, BindingResult result) {
        if (!result.hasErrors()) {
            cohortRepository.save(cohort);
        }
        return "redirect:/cohort";
    }

    @GetMapping("/delete/{cohortId}")
    private String deleteCohort(@PathVariable("cohortId") Long cohortId, Model model) {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);

        if(optionalCohort.isPresent()){
            try {
                cohortRepository.delete(optionalCohort.get());
            }catch (DataIntegrityViolationException dataIntegrityViolationException){
                System.out.println(dataIntegrityViolationException);
                model.addAttribute("errorMessage",
                        "This Cohort can't be deleted due to relation to other entities");
                return "error";
            }
        }
        return "redirect:/cohort";
    }
}
