package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.service.impl.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/logout")
    public ResponseEntity<SaveResponseDto> logout(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        tokenBlacklistService.blacklistToken(jwt);
        var response = new SaveResponseDto("0", "OK", "El ususairo ha cerrado sesión satisfactoriamente.");
        return ResponseEntity.ok(response);
    }
}