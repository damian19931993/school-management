package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody TeacherDto request) {
        try {
            User createdUser = teacherService.create(request);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El docente fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear el docente: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @PreAuthorize("hasRole('DIRECTIVO')")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id) {
        var response = teacherService.findById(id);
        return ResponseEntity.ok(response);
    }


}
