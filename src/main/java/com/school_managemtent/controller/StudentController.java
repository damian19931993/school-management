package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody StudentDto request) {
        try {
            User createdUser = studentService.create(request);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El estudiante fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear el estudiante: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getTeacher(@PathVariable Long id) {
        var response = studentService.findById(id);
        return ResponseEntity.ok(response);
    }
}
