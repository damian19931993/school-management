package com.school_managemtent.controller;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.service.impl.TokenBlacklistService;
import com.school_managemtent.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class TokenValidationController {

    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public TokenValidationController(JwtUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @GetMapping("/validateToken")
    public ResponseEntity<SaveResponseDto> validateToken(
            @RequestHeader(value = "Authorization", required = false) String header
    ) {
        // Si no viene la cabecera o no empieza con Bearer
        if (header == null || !header.startsWith("Bearer ")) {
            SaveResponseDto response = new SaveResponseDto(
                    "1",
                    "ERROR",
                    "No se ha provisto un token Bearer válido en el header Authorization."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = header.substring(7);

        // Si el token está en la blacklist
        if (tokenBlacklistService.isTokenBlacklisted(token)) {
            SaveResponseDto response = new SaveResponseDto(
                    "2",
                    "ERROR",
                    "El token se encuentra en la blacklist."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        try {
            // Intentamos extraer el username. Si falla, es un token inválido o expirado
            jwtUtil.extractUsername(token);

            // Si todo fue bien, retornamos el JSON con code=0, description=OK, etc.
            SaveResponseDto response = new SaveResponseDto(
                    "0",
                    "OK",
                    "El token se ha validado correctamente."
            );
            System.out.println("Token validado correctamente.");
            return ResponseEntity.ok(response);

        } catch (JwtException e) {
            // Si el token no es válido
            SaveResponseDto response = new SaveResponseDto(
                    "3",
                    "ERROR",
                    "El token es inválido o ha expirado."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
