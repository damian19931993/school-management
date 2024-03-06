package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.entity.StudentDetail;
import com.schoolmanagement.com.schoolmanagement.service.CourseService;
import com.schoolmanagement.com.schoolmanagement.service.StudentDetailService;
import com.schoolmanagement.com.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentDetailService studentDetailService;

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public String home(){
        return "student/home";
    }

    @GetMapping("/create")
    public String create(Model model){
        Student student = new Student();
        StudentDetail studentDetail = student.getStudentDetail();
        model.addAttribute("student",student);
        model.addAttribute("studentDetail", studentDetail);
        return "student/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student/student-form";
        }

        // Verificar si ya existe un estudiante con el mismo DNI
        Optional<Student> existingStudent = Optional.ofNullable(studentService.findByDni(student.getDni()));
        if (existingStudent.isPresent() && (student.getId()!=0 || existingStudent.get().getId()!=(student.getId()))) {
            model.addAttribute("errorMessage", "El DNI ya está registrado para otro estudiante.");
            return "student/student-form"; // Retorna al formulario mostrando el mensaje de error
        }

        try {
            if (student.getStudentDetail() != null) {
                student.getStudentDetail().setStudent(student); // Establece la relación inversa
            }
            studentService.save(student);

            return "redirect:/student"; //
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Ocurrió un error al guardar el estudiante. Por favor, intente nuevamente.");
            return "student/student-form"; // Retorna al formulario en caso de error
        }
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam("dni") String dni, Model model) {
        Optional<Student> studentOptional = Optional.ofNullable(studentService.findByDni(dni));
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("student", student);
            // Asumiendo que tienes un método en tu servicio para obtener detalles por ID de estudiante
            StudentDetail studentDetail = studentDetailService.findByStudentId(student.getId());
            model.addAttribute("studentDetail", studentDetail);
            return "student/student-detail"; // Nombre del archivo HTML de la vista con los detalles
        } else {
            model.addAttribute("errorMessage", "No se encontró un estudiante con el DNI proporcionado.");
            return "redirect:/student";
        }
    }
    @GetMapping("/showAssignCourse")
    public String showAssignCoursesPage(Model model) {
        // Obtener todos los estudiantes activos
        List<Student> activeStudents = studentService.findAllActiveStudents();

        // Agregar los estudiantes activos al modelo
        model.addAttribute("students", activeStudents);

        // Retornar el nombre de la vista (archivo HTML)
        return "student/assign-course-home";
    }

    @GetMapping("/assignCourse/{studentId}")
    public String showAssignCourseForm(@PathVariable("studentId") int studentId, Model model) {
        // Buscar el estudiante por ID
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional == null) {
            // Manejar el caso en que el estudiante no se encuentre, por ejemplo, redirigir a una página de error
            return "redirect:/errorPage";
        }

        // Obtener todos los cursos activos
        List<Course> activeCourses = courseService.findAllActiveCourses();

        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());
            model.addAttribute("activeCourses", activeCourses);
        } else {
            // Manejar el caso de que el estudiante no se encuentre
            return "redirect:/errorPage";
        }

        // Agregar el estudiante y los cursos activos al modelo



        // Retornar la vista del formulario de asignación de cursos
        return "student/assign-course-form";
    }

    @PostMapping("/saveAssignedCourse")
    public String saveAssignedCourse(@RequestParam("studentId") int studentId,
                                     @RequestParam("courseIds") List<Integer> courseIds) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (!studentOptional.isPresent()) {
            return "redirect:/errorPage";
        }

        Student student = studentOptional.get();
        List<Course> selectedCourses = courseService.findAllByIds(courseIds);

        // Limpiar las asignaciones de cursos anteriores si es necesario
        // Esto depende de la lógica de tu negocio


        for (Course course : selectedCourses) {
            student.addCourse(course);
        }

        // Guarda el estudiante con los cursos asignados
        studentService.save(student);

        return "redirect:/student/showAssignCourse";
    }


}
