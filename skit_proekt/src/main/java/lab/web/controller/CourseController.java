package lab.web.controller;

import lab.model.Course;
import lab.model.TeacherInterface;
import lab.model.Type;
import lab.service.CourseService;
import lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> courses=courseService.listAll();
        Collections.sort(courses);
        model.addAttribute("courses", courses);
        return "listCourses";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(@RequestParam(required = false) String error,
                             Model model) {
        if(error!=null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<TeacherInterface> teachers=this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("courseTypes", lab.model.Type.values());
        return "add-course";
    }


    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacherId, @RequestParam Type type){
        this.courseService.save(name, description,teacherId, type);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.courseService.findById(id).isPresent()){
            Course course=this.courseService.findById(id).get();
            List<TeacherInterface> teachers=this.teacherService.findAll();
            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course);
            model.addAttribute("courseTypes", lab.model.Type.values());
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFouund";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@PathVariable Long id){
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }
}
