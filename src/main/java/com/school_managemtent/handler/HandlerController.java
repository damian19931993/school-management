package com.school_managemtent.handler;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.exception.BadUsernameOoPasswordExceptionResponseDto;
import com.school_managemtent.exception.BadUsernameOrPasswordException;
import com.school_managemtent.exception.NonAvailableDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler(NonAvailableDataBaseException.class)
    public ResponseEntity<SaveResponseDto> handleNonAvailableDataBaseException(NonAvailableDataBaseException ex) {
        SaveResponseDto response = new SaveResponseDto();
        response.setCode("1");
        response.setDescription("Error de base de datos.");
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadUsernameOrPasswordException.class)
    public ResponseEntity<BadUsernameOoPasswordExceptionResponseDto> handleBadUsernameOrPasswordException(BadUsernameOrPasswordException ex) {
        BadUsernameOoPasswordExceptionResponseDto response = new BadUsernameOoPasswordExceptionResponseDto("1", "El usuario o contraseña son inválidos.");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
