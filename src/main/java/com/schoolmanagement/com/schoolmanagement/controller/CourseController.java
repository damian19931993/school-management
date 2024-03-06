package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.Assistant;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping("")
    public String listActiveCourses(Model model) {
        List<Course> activeCourses = courseService.findAllActiveCourses();
        model.addAttribute("courses", activeCourses);
        return "course/home"; //
    }

    @GetMapping("/create")
    public String showFormForAdd(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:/course";
    }

    @GetMapping("/details/{id}")
    public String showCourseDetails(@PathVariable("id") int courseId, Model model) {
        Optional<Course> courseOptional = Optional.ofNullable(courseService.findById(courseId));

        if (!courseOptional.isPresent()) {
            // Si el curso no existe, redirigir a una página de error o lista de cursos
            return "redirect:/course/list";
        }

        Course course = courseOptional.get();
        // Asumiendo que tienes un método en tu servicio para obtener estudiantes por curso
        Set<Student> studentsAssignedToCourse = course.getStudents();

        model.addAttribute("course", course);
        model.addAttribute("students", studentsAssignedToCourse);

        // Retornar la vista con los detalles del curso y la lista de estudiantes asignados
        return "course/details"; // Asegúrate de tener esta vista (details.html) bajo el directorio /templates/course
    }
}
