package com.school_managemtent.controller;

import com.school_managemtent.dto.DirectivoDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.service.DirectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SaveResponseDto> create(@RequestBody DirectivoDto request) {
        try {
            User createdUser = directivoService.create(request);
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "El directivo fue creado correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear el docente: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectivoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(directivoService.findById(id));
    }
}
