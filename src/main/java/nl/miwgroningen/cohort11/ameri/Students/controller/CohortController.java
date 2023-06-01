package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Cohort;
import nl.miwgroningen.cohort11.ameri.Students.repository.CohortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project Manages all user requests and interactions with Cohort pages
 */
@RequestMapping("/cohort")
@Controller
@RequiredArgsConstructor
public class CohortController {
    private final CohortRepository cohortRepository;

    @GetMapping({"","/overview"})
    private String showCohortOverview(Model model){
        model.addAttribute("cohorts", cohortRepository.findAll());
        return "cohortOverview";
    }

    @GetMapping("/new")
    private String ShowCohortNewEditForm(Model model){
        model.addAttribute("cohort", new Cohort());
        return "cohortNewEditForm";
    }
    @PostMapping("")
    private String SaveUpdateCohort(@ModelAttribute("cohort") Cohort cohort, BindingResult result){
        if(!result.hasErrors()){
            cohortRepository.save(cohort);
        }
        return "redirect:/cohort";
    }
}
