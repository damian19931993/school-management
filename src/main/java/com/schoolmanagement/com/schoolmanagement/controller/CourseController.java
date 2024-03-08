package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.Assistant;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.service.CourseService;
import com.schoolmanagement.com.schoolmanagement.service.SchoolSubjectService;
import com.schoolmanagement.com.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    SchoolSubjectService schoolSubjectService;

    @Autowired
    StudentService studentService;

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

    @GetMapping("/assignSubjects/{courseId}")
    public String showAssignSubjects(@PathVariable("courseId") Integer courseId, Model model) {
        // Busca todas las materias activas. Asegúrate de tener un método en tu servicio que haga esta consulta.
        List<SchoolSubject> activeSubjects = schoolSubjectService.findAllActiveSubjects();

        // Agrega los objetos necesarios al modelo para ser accesibles desde la plantilla Thymeleaf
        model.addAttribute("courseId", courseId);
        model.addAttribute("activeSubjects", activeSubjects);

        // Retorna el nombre de la plantilla Thymeleaf donde se mostrarán las materias activas para asignar al curso
        return "course/assign-subject-form";
    }

    @PostMapping("/saveAssignedSubjects/{courseId}")
    public String saveAssignedSubjects(@PathVariable("courseId") Integer courseId, @RequestParam List<Integer> subjectIds, RedirectAttributes redirectAttributes) {
        try {
            // El método assignSubjectsToCourse ahora solo necesita el ID del curso y los IDs de las materias,
            // ya que buscará el curso y las materias dentro del mismo servicio
            courseService.assignSubjectsToCourse(courseId, subjectIds);

            redirectAttributes.addFlashAttribute("successMessage", "Materias asignadas exitosamente al curso.");
        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger en lugar de printStackTrace en un entorno de producción
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al asignar las materias al curso.");
        }

        return "redirect:/course"; // Asegúrate de redireccionar a la URL correcta después de completar la operación
    }



}
