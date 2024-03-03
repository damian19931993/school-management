package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjects;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjectsId;
import com.schoolmanagement.com.schoolmanagement.service.SchoolSubjectService;
import com.schoolmanagement.com.schoolmanagement.service.TeacherService;
import com.schoolmanagement.com.schoolmanagement.service.TeacherSubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolSubjectService schoolSubjectService;

    @Autowired
    TeacherSubjectsService teacherSubjectsService;

    @GetMapping("")
    public String home(){
        return "teacher/home";
    }

    @GetMapping("/create")
    public String create(Model model){
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

    @PostMapping("/teacher/deleteAssignment/{teacherId}")
    public String deleteAssignment(@RequestParam("teacherId") int teacherId, @RequestParam("subjectId") int subjectId, RedirectAttributes redirectAttributes) {
        teacherSubjectsService.deleteAssignment(teacherId, subjectId);
        redirectAttributes.addFlashAttribute("success", "Asignación eliminada correctamente.");
        return "redirect:/teacher/showAssignmentPage";
    }

    @GetMapping("/deleteAssignment/{teacherId}")
    public String showDeleteAssignmentPage(@PathVariable int teacherId, Model model) {
        // Buscar las asignaciones de materias para el profesor específico
        List<TeacherSubjects> assignments = teacherSubjectsService.findAssignmentsByTeacherId(teacherId);
        model.addAttribute("assignments", assignments);
        model.addAttribute("teacherId", teacherId);
        return "teacher/delete-assignment-form";
    }



}
