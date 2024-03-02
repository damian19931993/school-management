package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.entity.StudentDetail;
import com.schoolmanagement.com.schoolmanagement.service.StudentDetailService;
import com.schoolmanagement.com.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentDetailService studentDetailService;

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

}
