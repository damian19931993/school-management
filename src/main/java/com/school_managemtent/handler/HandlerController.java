package com.school_managemtent.handler;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.exception.BadUsernameOoPasswordExceptionResponseDto;
import com.school_managemtent.dto.exception.NoExistingEntityResponseDto;
import com.school_managemtent.dto.response.EntityGenericResponse;
import com.school_managemtent.entity.log.TransactionLog;
import com.school_managemtent.exception.*;
import com.school_managemtent.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
public class HandlerController {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

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

    @ExceptionHandler(ExistingEntityException.class)
    public ResponseEntity<NoExistingEntityResponseDto> handleExistingEntityException(ExistingEntityException ex) {
        NoExistingEntityResponseDto response = new NoExistingEntityResponseDto();
        response.setCode("2");
        response.setDescription("Entidad ya existente.");
        response.setMessage(ex.getMessage());
        createLog("Creación de entidad fallida.", ex.getUsername() ,ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<SaveResponseDto> handleNotFoundEntityException(NotFoundEntityException ex){
        SaveResponseDto response = new SaveResponseDto();
        response.setCode("3");
        response.setDescription("Entidad no encontrada.");
        response.setMessage(ex.getMessage());
        createLog("Entidad no encontrada.", ex.getUsername(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NotFoundRelationResponseException.class)
    public ResponseEntity<SaveResponseDto> handleNotFoundRelationResponseException(NotFoundRelationResponseException ex){
        SaveResponseDto response = new SaveResponseDto();
        response.setCode("3");
        response.setDescription("Relación no encontrada.");
        response.setMessage(ex.getMessage());
        createLog("Relación no encontrada.", ex.getUsername(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private TransactionLog createLog(String operation, String username, String details) {
        TransactionLog log = new TransactionLog();
        log.setTransactionId((UUID.randomUUID().toString()));
        log.setOperation(operation);
        log.setPerformedBy(username);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());
        return transactionLogRepository.save(log);
    }
}
