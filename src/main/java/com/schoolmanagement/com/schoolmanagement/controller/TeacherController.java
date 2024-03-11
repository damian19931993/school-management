package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.*;
import com.schoolmanagement.com.schoolmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolSubjectService schoolSubjectService;


    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    MarkService markService;


    @Autowired
    TeacherSubjectService teacherSubjectService;
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
        List<Teacher> allTeachers = teacherService.findAllActiveTeachers();
        model.addAttribute("teachers", allTeachers);
        return "teacher/assign-subject-home"; //
    }

    @GetMapping("/assignSubjectForm/{teacherId}")
    public String showAssignSubjectForm(@PathVariable("teacherId") int teacherId, Model model) {
        Teacher teacher = teacherService.findById(teacherId);
        List<SchoolSubject> subjects = schoolSubjectService.findAll();
        List<Course> courses = courseService.findAllActiveCourses();
        model.addAttribute("teacher", teacher);
        model.addAttribute("subjects", subjects);
        model.addAttribute("courses", courses);
        return "teacher/assign-subject-form";
    }

    @PostMapping("/saveAssignment")
    public String assignSubject(@RequestParam("teacherId") int teacherId,
                                @RequestParam("subjectId") int subjectId,
                                @RequestParam(value = "courseId", required = false) Integer courseId, // Opcional, dependiendo de si siempre se asigna una materia con un curso específico
                                RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(teacherId);
        SchoolSubject subject = schoolSubjectService.findById(subjectId);
        Course course = null;
        if (courseId != null) {
            course = courseService.findById(courseId);
        }

        if (teacher != null && subject != null) {
            TeacherSubjects teacherSubjects = new TeacherSubjects();

            teacherSubjects.setTeacher(teacher);
            teacherSubjects.setSchoolSubject(subject);
            teacherSubjects.setActive(true); // Activar esta asignación por defecto
            if (course != null) {
                teacherSubjects.setCourse(course); // Asignar curso si está presente
            }

            teacherSubjectService.save(teacherSubjects);

            redirectAttributes.addFlashAttribute("success", "Materia asignada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo encontrar el profesor o la materia.");
        }

        return "redirect:/teacher/showAssignmentPage"; // Asumiendo que tienes una vista que lista las asignaciones
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
    @GetMapping("/courses")
    public String showTeacherCourses(Model model, Principal principal) {
        String username = principal.getName();
        Teacher teacher = teacherService.findByUsername(username);
        List<Course> activeCourses = teacherService.findAllActiveCourses(teacher);
        List<SchoolSubject> activeSubjects = schoolSubjectService.findActiveSubjectsByTeacher(teacher);
        model.addAttribute("activeCourses", activeCourses);
        model.addAttribute("activeSubjects",activeSubjects);
        model.addAttribute("teacher", teacher);
        return "teacher/course";
    }
    @GetMapping("/courseDetails/{courseId}")
    public String showCourseDetails(@PathVariable("courseId") int courseId, Model model) {
        Course course = courseService.findById(courseId);
        if (course != null) {
            List<Student> activeStudents = studentService.findActiveStudentsByCourse(course);
            model.addAttribute("course", course);
            model.addAttribute("activeStudents", activeStudents);
        }
        return "teacher/students";
    }
    @GetMapping("/showMarkHome/{courseId}/{subjectId}")
    public String gradesHome(@PathVariable int courseId, @PathVariable int subjectId, Model model, Principal principal) {
        String username = principal.getName();
        Teacher teacher = teacherService.findByUsername(username);
        Course course = courseService.findById(courseId);
        SchoolSubject subject = schoolSubjectService.findById(subjectId);
        List<Student> students = studentService.findActiveStudentsByCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("subject", subject);
        model.addAttribute("students", students);

        return "teacher/mark/home";
    }

    @GetMapping("/addMark/{courseId}/{subjectId}/{studentId}")
    public String showAddGradeForm(@PathVariable int courseId, @PathVariable int subjectId, @PathVariable int studentId, Model model, Principal principal) {
        String username = principal.getName();
        Teacher teacher = teacherService.findByUsername(username);
        Mark mark = new Mark();
        Course course = courseService.findById(courseId);
        SchoolSubject subject = schoolSubjectService.findById(subjectId);
        Student student = studentService.findById2(studentId);
        mark.setCourse(course);
        mark.setSubject(subject);
        mark.setStudent(student);
        mark.setTeacher(teacher);
        model.addAttribute("mark", mark);
        model.addAttribute("student",student);
        model.addAttribute("subject",subject);
        model.addAttribute("teacher",teacher);
        model.addAttribute("course",course);
        model.addAttribute("courseId",course.getId());
        return "teacher/mark/form";
    }

    @PostMapping("/saveMark")
    public String processAddGradeForm(@ModelAttribute Mark mark, RedirectAttributes redirectAttributes) {
        // Aquí asumimos que tienes un servicio para guardar la calificación
        markService.saveMark(mark);
        redirectAttributes.addAttribute("courseId", mark.getCourse().getId());
        redirectAttributes.addAttribute("subjectId", mark.getSubject().getId());
        return "redirect:/teacher/showMarkHome/{courseId}/{subjectId}"; // Redirige a donde quieras después de guardar la calificación
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        // El segundo parámetro a 'true' permite nulo
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }




}
