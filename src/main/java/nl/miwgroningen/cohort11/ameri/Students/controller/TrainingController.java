package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.miwgroningen.cohort11.ameri.Students.model.Training;
import nl.miwgroningen.cohort11.ameri.Students.repository.TrainingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Mohammed Alameri on 01/06/2023.
 * @project handles all the pages of training program
 */
@Controller
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingRepository trainingRepository;

    @GetMapping({"/", "/training/overview"})
    private String showTrainingOverview(Model model){
        model.addAttribute("trainings", trainingRepository.findAll());
        model.addAttribute("training", new Training());
        return "trainingOverview";
    }

    @PostMapping("")
    private String SaveUpdateTraining (@ModelAttribute("training") Training training, BindingResult result){
           if(!result.hasErrors()){
               trainingRepository.save(training);
           }

           return "redirect:/";
    }

}
