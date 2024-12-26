package com.school_managemtent.controller;

import com.school_managemtent.dto.DirectivoDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.DirectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/directivo")
public class DirectivoController {

    private final DirectivoService directivoService;

    @Autowired
    public DirectivoController(DirectivoService directivoService) {
        this.directivoService = directivoService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody DirectivoDto request) {
        return ResponseEntity.ok(directivoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectivoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(directivoService.findById(id));
    }
}
