package nl.miwgroningen.cohort11.ameri.Students.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Course;
import nl.miwgroningen.cohort11.ameri.Students.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Mohammed Alameri on 01/06/2023.
 * @project handles all the course pages and requests
 */
@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping({"/", "/course/overview"})
    private String showCourseOverview(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("course", new Course());
        return "courseOverview";
    }

    @PostMapping("/")
    private String SaveUpdateCourse (@ModelAttribute("course") Course course, BindingResult result){
           if(!result.hasErrors()){
               courseRepository.save(course);
           }

           return "redirect:/";
    }

    @GetMapping("/course/delete/{courseId}")
    private String deleteCourse(@PathVariable("courseId") Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourse.isPresent()) {
            courseRepository.delete(optionalCourse.get());
        }

        return "redirect:/";
    }

}
