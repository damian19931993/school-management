package com.school_managemtent.controller;

import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.StudentService;
import com.school_managemtent.service.TeacherService;
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
    public ResponseEntity<User> createTeacherUser(@RequestBody StudentDto request) {
        var response = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getTeacher(@PathVariable Long id) {
        var response = studentService.findById(id);
        return ResponseEntity.ok(response);
    }
}
