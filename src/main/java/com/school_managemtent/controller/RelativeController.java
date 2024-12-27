package com.school_managemtent.controller;

import com.school_managemtent.dto.DirectivoDto;
import com.school_managemtent.dto.RelativeDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relative")
public class RelativeController {

    private final RelativeService relativeService;

    @Autowired
    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody RelativeDto request) {
        return ResponseEntity.ok(relativeService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelativeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(relativeService.findById(id));
    }
}
