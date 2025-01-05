package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.dto.response.AllTeachersResponseDto;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.exception.ExistingEntityException;
import com.school_managemtent.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody TeacherDto request, @RequestHeader("username") String username) {
        try {
            teacherService.create(request, username);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El docente fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (DataIntegrityViolationException e) {
            throw new ExistingEntityException("El docente con dni: " + request.getDni() +
                    " O con email: " + request.getEmail() + " ya existe.", username);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id, @RequestHeader("username") String username) {
        var response = teacherService.findById(id, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('DIRECTIVO')")
    public ResponseEntity<AllTeachersResponseDto> findAll(@RequestHeader("username") String username) {
        return ResponseEntity.ok(teacherService.findAll(username));
    }
    
}
