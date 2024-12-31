package com.school_managemtent.controller;

import com.school_managemtent.dto.RelativeStudentDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.TeacherStudentDto;
import com.school_managemtent.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher-student")
public class TeacherStudentController {

    private final TeacherStudentService teacherStudentService;

    @Autowired
    public TeacherStudentController(TeacherStudentService teacherStudentService) {
        this.teacherStudentService = teacherStudentService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> linkRelativeToStudent(@RequestBody TeacherStudentDto request) {
        try {
            teacherStudentService.linkTeacherToStudent(
                    request.getTeacherId(),
                    request.getStudentId(),
                    request.isActive()
            );

            // Si todo va bien, creamos el SaveResponseDto de éxito
            SaveResponseDto responseDto = new SaveResponseDto(
                    "0",
                    "OK",
                    "La relación se creó correctamente."
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (RuntimeException e) {
            // Manejo de la excepción: devolvemos un SaveResponseDto de error
            SaveResponseDto responseDto = new SaveResponseDto(
                    "99",
                    "ERROR_UNKNOWN",
                    "Error al crear la relación: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }

        // Podrías agregar más endpoints, por ejemplo:
        // - GET /api/relative-student/relatives/{studentId} para obtener todos los familiares de un estudiante
        // - GET /api/relative-student/students/{relativeId} para obtener todos los estudiantes de un familiar
        // - DELETE /api/relative-student/unlink para desvincular, etc.
    }
}
