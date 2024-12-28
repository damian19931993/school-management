package com.school_managemtent.controller;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.PreceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SaveResponseDto> create(@RequestBody PreceptorDto request) {
        try {
            User createdUser = preceptorService.create(request);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El preceptor fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear el preceptor: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreceptorDto> getPreceptor(@PathVariable Long id) {
        var response = preceptorService.findById(id);
        return ResponseEntity.ok(response);
    }
}
