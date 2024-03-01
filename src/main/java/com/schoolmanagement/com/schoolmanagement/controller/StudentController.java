package com.schoolmanagement.com.schoolmanagement.controller;

import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("")
    public String home(){
        return "student/home";
    }

    @GetMapping("/create")
    public String create(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "student/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            // No es necesario verificar si el ID existe para un nuevo estudiante, JPA se encargará de asignar uno si es un nuevo registro
            studentService.save(student); // Guardar o actualizar el estudiante en la base de datos
            return "redirect:/student"; // Redirige a la vista que muestra la lista de estudiantes
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Ocurrió un error al guardar el estudiante. Por favor, intente nuevamente.");
            return "student/student-form"; // Retorna al formulario de estudiante en caso de error
        }
    }

}
