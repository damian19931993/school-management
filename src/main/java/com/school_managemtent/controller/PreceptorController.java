package com.school_managemtent.controller;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.PreceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preceptor")
public class PreceptorController {

    private final PreceptorService preceptorService;

    @Autowired
    public PreceptorController(PreceptorService preceptorService) {
        this.preceptorService = preceptorService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody PreceptorDto request) {
        var response = preceptorService.create(request);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreceptorDto> getPreceptor(@PathVariable Long id) {
        var response = preceptorService.findById(id);
        return ResponseEntity.ok(response);
    }
}
