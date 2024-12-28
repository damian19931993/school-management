package com.school_managemtent.controller;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.RelativeDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SaveResponseDto> create(@RequestBody RelativeDto request) {
        try {
            User createdUser = relativeService.create(request);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El familiar fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear el familiar: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<RelativeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(relativeService.findById(id));
    }
}
