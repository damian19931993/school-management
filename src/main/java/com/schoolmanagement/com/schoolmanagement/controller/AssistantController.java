package com.schoolmanagement.com.schoolmanagement.controller;


import com.schoolmanagement.com.schoolmanagement.entity.*;
import com.schoolmanagement.com.schoolmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/assistant")
public class AssistantController {

    @Autowired
    AssistantService assistantService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserService userService;

    @Autowired
    SchoolSubjectService schoolSubjectService;

    @GetMapping("")
    public String listAssistants(Model theModel) {
        List<Assistant> theAssistants = assistantService.findAll();
        theModel.addAttribute("assistants", theAssistants);
        return "assistant/home";
    }

    @GetMapping("/create")
    public String showFormForAdd(Model theModel) {
        Assistant theAssistant = new Assistant();
        theModel.addAttribute("assistant", theAssistant);
        return "assistant/assistant-form";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
    }
    @PostMapping("/save")
    public String saveAssistant(@ModelAttribute("assistant") Assistant theAssistant) {
        // Guardar el preceptor usando el servicio
        assistantService.save(theAssistant);
        return "redirect:/assistant"; //
    }

    @GetMapping("/create/{assistantId}")
    public String showAssignCourseForm(@PathVariable("assistantId") int assistantId, Model model) {
        // Obtener todos los cursos activos
        List<Course> activeCourses = courseService.findAllActiveCourses();

        // Agregar los cursos activos y el ID del asistente al modelo
        model.addAttribute("activeCourses", activeCourses);
        model.addAttribute("assistantId", assistantId);

        // Retornar el nombre de la plantilla (vista) que muestra los cursos con checkboxes
        return "assistant/assign-course-form";
    }

    @PostMapping("/assignCourses")
    public String saveAssignedCourses(@RequestParam("assistantId") int assistantId,
                                      @RequestParam("courseIds") List<Integer> courseIds) {
        // Encuentra el asistente por ID
        Assistant assistant = assistantService.findById(assistantId);

        if (assistant != null) {
            // Encuentra los cursos actualmente asignados al asistente
            List<Course> currentlyAssignedCourses = assistant.getCourses();

            // Encuentra los cursos seleccionados por sus IDs
            List<Course> selectedCourses = courseService.findAllByIds(courseIds);

            // Agrega todos los cursos seleccionados a la lista de cursos actualmente asignados
            // Esto asegura que se mantengan las asignaciones previas y se agreguen las nuevas
            // Se asume que `selectedCourses` no contiene duplicados o cursos ya asignados
            // De lo contrario, deberías filtrar `selectedCourses` para evitar duplicados
            currentlyAssignedCourses.addAll(selectedCourses);

            // Asigna los cursos actualizados al asistente
            assistant.setCourses(currentlyAssignedCourses);

            // Guarda el asistente con los cursos asignados
            assistantService.save(assistant);

            // Redirige a una página (por definir), por ejemplo, lista de asistentes
            return "redirect:/assistant";
        }

        // Si el asistente no se encuentra, redirige a una página de error o lista de asistentes
        return "redirect:/errorPage";
    }

    @GetMapping("/assignRole/{assistantId}")
    public String showAssignRoleForm(@PathVariable("assistantId") int assistantId, Model model) {
        Assistant assistant = assistantService.findById(assistantId);
        if (assistant != null) {
            User user = new User(); // Crear una nueva instancia de User
            model.addAttribute("user", user);
            model.addAttribute("authorities", authorityService.findAll()); //
            model.addAttribute("assistantId", assistantId);
            return "assistant/assign-role-form"; // Nombre del archivo HTML del formulario
        }
        return "redirect:/assistant"; // Redirigir si el docente no se encuentra
    }

    @PostMapping("/saveUser/{assistantId}")
    public String saveUser(@ModelAttribute("user") User user, @PathVariable("assistantId") int assistantId, Model model) {
        // Buscar al preceptor por ID
        Optional<Assistant> assistantOptional = Optional.ofNullable(assistantService.findById(assistantId));
        if (assistantOptional.isPresent()) {
            Assistant assistant = assistantOptional.get();

            // Guardar el usuario en la base de datos
            User savedUser = userService.saveUser(user);

            // Asignar el usuario guardado al docente
            assistant.setUser(savedUser); // Asegúrate de que Teacher tenga un método setUser(User user)

            // Guardar el docente con el usuario asignado
            assistantService.save(assistant);

            // Redirigir a la lista de docentes o a donde prefieras
            return "redirect:/assistant";
        } else {
            // Si el docente no se encuentra, manejar el error adecuadamente
            model.addAttribute("errorMessage", "Docente no encontrado con ID: " + assistantId);
            return "error"; // O redirigir a una página de error o formulario adecuado
        }
    }
    @GetMapping("/myCourses")
    public String Courses(Model model, Principal principal) {
        // Obtén el nombre de usuario del preceptor desde el Principal
        String username = principal.getName();

        // Encuentra al preceptor por su username (esto podría requerir un método en tu servicio)
        Optional<Assistant> optionalAssistant= assistantService.findByUsername(username);

        if (optionalAssistant.isPresent()) {
            Assistant assistant = optionalAssistant.get();
            List<Course> activeCourses = courseService.findActiveCoursesByAssistant(assistant.getId());

            // Para cada curso, encuentra sus materias activas y añádelas a un Map o una estructura similar
            Map<Course, List<SchoolSubject>> coursesWithSubjects = new HashMap<>();
            for (Course course : activeCourses) {
                List<SchoolSubject> activeSubjects = schoolSubjectService.findActiveSubjectsByCourse(course.getId());
                coursesWithSubjects.put(course, activeSubjects);
            }

            model.addAttribute("coursesWithSubjects", coursesWithSubjects);
        }

        return "assistant/assistant"; // Asegúrate de tener una plantilla Thymeleaf correspondiente
    }

    @GetMapping("/courseStudents/{courseId}")
    public String listStudentsByCourse(@PathVariable("courseId") Integer courseId, Model model) {
        // Buscar los alumnos asociados al curso con el ID proporcionado
        List<Student> students = studentService.findStudentsByCourseId(courseId);

        // Agregar la lista de estudiantes al modelo para que pueda ser accesible en la vista
        model.addAttribute("students", students);

        // Nombre del archivo HTML de la vista (dentro de src/main/resources/templates)
        return "assistant/list-students";
    }




}
