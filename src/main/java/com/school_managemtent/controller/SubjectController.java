package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.dto.response.EntityGenericResponse;
import com.school_managemtent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<EntityGenericResponse<SubjectDto>> getSubject(@PathVariable Long id, @RequestHeader("username") String username) {
        var response = subjectService.findById(id, username);
        EntityGenericResponse<SubjectDto> genericResponse = new EntityGenericResponse<>("0","OK",response);
        return ResponseEntity.ok(genericResponse);
    }
}
