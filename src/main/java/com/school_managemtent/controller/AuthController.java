package com.school_managemtent.controller;


import com.school_managemtent.dto.AuthResponseDto;
import com.school_managemtent.exception.BadUsernameOrPasswordException;
import com.school_managemtent.service.impl.MyUserDetailsService;
import com.school_managemtent.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadUsernameOrPasswordException("Invalid username/password");
        }

        // Generar el token JWT
        String token = jwtUtil.generateToken(authRequest.getUsername());

        // Obtener los detalles del usuario usando MyUserDetailsService
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        // Extraer el rol del usuario
        String role = userDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("ROLE_USER"); // Rol por defecto si no hay roles asignados

        // Crear la respuesta con token y rol
        AuthResponseDto response = new AuthResponseDto(token, role);
        return ResponseEntity.ok(response);
    }
}

class AuthRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
