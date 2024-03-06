package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.*;
import com.schoolmanagement.com.schoolmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolSubjectService schoolSubjectService;

    @Autowired
    TeacherSubjectsService teacherSubjectsService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;
    @GetMapping("")
    public String home(Model model) {
        List<Teacher> teachers = teacherService.findAllActiveTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-form";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, BindingResult result, Model model) {
        try {
            // Procesa y guarda el docente en la base de datos
            teacherService.save(teacher);
            return "redirect:/teacher"; // Redirige a la lista de docentes o a la página que prefieras
        } catch (Exception e) {
            // Maneja posibles errores, por ejemplo, en caso de DNI duplicado
            model.addAttribute("errorMessage", "Error al guardar el docente. Detalles: " + e.getMessage());
            return "teacher/teacher-form"; // Vuelve al formulario mostrando el mensaje de error
        }
    }

    @GetMapping("/showAssignmentPage")
    public String showAssignmentPage(Model model) {
        List<Teacher> allTeachers = teacherService.findAll();
        model.addAttribute("teachers", allTeachers);
        return "teacher/assign-subject-home"; //
    }

    @GetMapping("/assignSubjectForm/{teacherId}")
    public String showAssignSubjectForm(@PathVariable("teacherId") int teacherId, Model model) {
        Teacher teacher = teacherService.findById(teacherId);
        List<SchoolSubject> subjects = schoolSubjectService.findAll();
        model.addAttribute("teacher", teacher);
        model.addAttribute("subjects", subjects);
        return "teacher/assign-subject-form";
    }

    @PostMapping("/saveAssignment")
    public String assignSubject(@RequestParam("teacherId") int teacherId,
                                @RequestParam("subjectId") int subjectId,
                                RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(teacherId);
        SchoolSubject subject = schoolSubjectService.findById(subjectId);

        if (teacher != null && subject != null) {
            TeacherSubjectsId id = new TeacherSubjectsId(teacherId, subjectId);
            TeacherSubjects teacherSubjects = new TeacherSubjects();

            teacherSubjects.setId(id);
            teacherSubjects.setTeacher(teacher);
            teacherSubjects.setSchoolSubject(subject);
            teacherSubjects.setActive(true); // Asumiendo que quieres activar esta asignación por defecto

            teacherSubjectsService.save(teacherSubjects);

            redirectAttributes.addFlashAttribute("success", "Materia asignada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo encontrar el profesor o la materia.");
        }

        return "redirect:/teacher/showAssignmentPage"; // Asumiendo que tienes una vista que lista las asignaciones
    }

    @GetMapping("/deleteAssignment")
    public String deleteAssignment(@RequestParam("teacherId") int teacherId, @RequestParam("subjectId") int subjectId, RedirectAttributes redirectAttributes) {
        teacherSubjectsService.deleteAssignment(teacherId, subjectId);
        redirectAttributes.addFlashAttribute("success", "Asignación eliminada correctamente.");
        return "redirect:/teacher/showAssignmentPage";
    }

    @GetMapping("/showDeleteAssignmentForm/{teacherId}")
    public String showDeleteAssignmentPage(@PathVariable int teacherId, Model model) {
        // Buscar las asignaciones de materias para el profesor específico
        List<TeacherSubjects> assignments = teacherSubjectsService.findAssignmentsByTeacherId(teacherId);
        model.addAttribute("assignments", assignments);
        model.addAttribute("teacherId", teacherId);
        return "teacher/delete-assignment-form";
    }

    @GetMapping("/showAssignRole")
    public String listActiveTeachers(Model model) {
        model.addAttribute("teachers", teacherService.findAllActiveTeachers());
        return "teacher/assign-role-home";
    }

    @GetMapping("/assignRole/{teacherId}")
    public String showAssignRoleForm(@PathVariable("teacherId") int teacherId, Model model) {
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher != null) {
            User user = new User(); // Crear una nueva instancia de User
            model.addAttribute("user", user);
            model.addAttribute("authorities", authorityService.findAll()); //
            model.addAttribute("teacherId", teacherId);
            return "teacher/assign-role-form"; // Nombre del archivo HTML del formulario
        }
        return "redirect:/teacher"; // Redirigir si el docente no se encuentra
    }

    @PostMapping("/saveUser/{teacherId}")
    public String saveUser(@ModelAttribute("user") User user, @PathVariable("teacherId") int teacherId, Model model) {
        // Buscar al docente por ID
        Optional<Teacher> teacherOptional = Optional.ofNullable(teacherService.findById(teacherId));
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();

            // Guardar el usuario en la base de datos
            User savedUser = userService.saveUser(user);

            // Asignar el usuario guardado al docente
            teacher.setUser(savedUser); // Asegúrate de que Teacher tenga un método setUser(User user)

            // Guardar el docente con el usuario asignado
            teacherService.save(teacher);

            // Redirigir a la lista de docentes o a donde prefieras
            return "redirect:/teacher";
        } else {
            // Si el docente no se encuentra, manejar el error adecuadamente
            model.addAttribute("errorMessage", "Docente no encontrado con ID: " + teacherId);
            return "error"; // O redirigir a una página de error o formulario adecuado
        }
    }

    @GetMapping("/myCourses")
    public String myCourses(Model model, Principal principal) {
        String username = principal.getName();
        Teacher teacher = teacherService.findByUsername(username);
        if (teacher != null) {
            // Asume que tienes un método para encontrar cursos activos por profesor
            Set<Course> activeCourses = teacher.getCourses(); // Modifica esto según cómo manejes los cursos activos
            model.addAttribute("activeCourses", activeCourses);
        }
        return "teacher/course";
    }

    @GetMapping("/showAssignCourseForm/{teacherId}")
    public String showAssignCoursesForm(@PathVariable("teacherId") int teacherId, Model model) {
        // Buscar el docente por ID
        Optional<Teacher> teacher = Optional.ofNullable(teacherService.findById(teacherId));
        if (teacher.isPresent()) {
            // Obtener la lista de cursos activos
            List<Course> activeCourses = courseService.findAllActiveCourses(); //
            model.addAttribute("teacher", teacher.get());
            model.addAttribute("courses", activeCourses);
            return "teacher/assign-course-form";
        } else {
            // Manejar el caso en que el docente no se encuentre
            return "redirect:/errorPage"; // O cualquier otra manejo que prefieras
        }
    }

    @PostMapping("/saveAssignedCourses")
    public String saveAssignedCourses(@RequestParam("teacherId") int teacherId, @RequestParam("assignedCourses") List<Integer> assignedCourseIds) {
        // Buscar el docente por ID
        Optional<Teacher> teacherOpt = Optional.ofNullable(teacherService.findById(teacherId));
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            // Limpiar las asignaciones existentes o manejarlas según la lógica de negocio
            // ...

            // Para cada ID de curso seleccionado, buscar el curso y asignarlo al docente
            for (Integer courseId : assignedCourseIds) {
                Course course = courseService.findById(courseId);
                teacher.addCourse(course); // Asegúrate de tener este método en tu entidad Teacher
            }

            // Guardar el docente con las nuevas asignaciones de cursos
            teacherService.save(teacher);

            return "redirect:/teacher"; // O redirige a donde prefieras
        } else {
            // Manejar el caso en que el docente no se encuentre
            return "redirect:/errorPage"; // O cualquier otro manejo que prefieras
        }
    }
    @GetMapping("/courseStudents/{id}")
    public String courseStudents(@PathVariable("id") int courseId, Model model) {
        // Suponiendo que tengas un servicio que pueda obtener los estudiantes por el ID del curso
        List<Student> students = studentService.findStudentsByCourseId(courseId);
        model.addAttribute("students", students);
        return "teacher/students"; // Nombre del archivo HTML que mostrará la lista de estudiantes
    }


}
