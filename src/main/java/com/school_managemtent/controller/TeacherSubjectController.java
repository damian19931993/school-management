package com.school_managemtent.controller;

import com.school_managemtent.dto.CourseTeacherDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.TeacherSubjectDto;
import com.school_managemtent.dto.response.RelationResponse;
import com.school_managemtent.entity.log.TransactionLog;
import com.school_managemtent.exception.NotFoundRelationResponseException;
import com.school_managemtent.service.TeacherSubjectService;
import com.school_managemtent.service.impl.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher-subject")
public class TeacherSubjectController {

    private final TeacherSubjectService teacherSubjectService;
    private final TransactionLogService transactionLogService;

    @Autowired
    public TeacherSubjectController(TeacherSubjectService teacherSubjectService, TransactionLogService transactionLogService) {
        this.teacherSubjectService = teacherSubjectService;
        this.transactionLogService = transactionLogService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> linkTeacherToSubject(@RequestBody TeacherSubjectDto request) {
        try {
            teacherSubjectService.linkTeacherToSubject(
                    request.getTeacherId(),
                    request.getSubjectId(),
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
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<RelationResponse> findSubjectIdSbYTeacherId(@RequestHeader("username") String username, @PathVariable Long teacherId ) {
        var response = teacherSubjectService.findSubjectByTeacherId(teacherId);
        if (response.isEmpty()) {
            throw new NotFoundRelationResponseException("No se encontraron materias para el docente.", username);
        }
        RelationResponse relationResponse = new RelationResponse();
        relationResponse.setCode("0");
        relationResponse.setDescription("OK");
        relationResponse.setRelation(response);
        transactionLogService.createLog("Encontrar las materias por docente", "Exito", username);
        return ResponseEntity.ok(relationResponse);

    }
}
