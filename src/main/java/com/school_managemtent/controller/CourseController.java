package com.school_managemtent.controller;

import com.school_managemtent.dto.CourseDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody CourseDto request) {
        var response = courseService.create(request);
        return ResponseEntity.ok(response);
    }
}
