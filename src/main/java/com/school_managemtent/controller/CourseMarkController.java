package com.school_managemtent.controller;

import com.school_managemtent.dto.CourseMarkDto;
import com.school_managemtent.dto.CoursePreceptorDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.service.CourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course-mark")
public class CourseMarkController {

    private final CourseMarkService courseMarkService;

    @Autowired
    public CourseMarkController(CourseMarkService courseMarkService) {
        this.courseMarkService = courseMarkService;
    }

    @PostMapping
    public ResponseEntity<SaveResponseDto> linkCourseToMark(@RequestBody CourseMarkDto request) {
        try {
            courseMarkService.linkCourseToMark(
                    request.getCourseId(),
                    request.getMarkId(),
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

}
