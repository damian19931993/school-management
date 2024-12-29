package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> create(@RequestBody SubjectDto request) {
        var response = subjectService.create(request);
        return ResponseEntity.ok(response);
    }
}
