package com.school_managemtent.controller;

import com.school_managemtent.dto.ChangePasswordRequestDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create (@RequestBody User request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/change-password")
    public ResponseEntity<SaveResponseDto> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        boolean isPasswordChanged = userService.changePassword(changePasswordRequestDto);
        SaveResponseDto response = new SaveResponseDto();
        if (isPasswordChanged) {
            response.setCode("0");
            response.setDescription("OK");
            response.setMessage("Password changed successfully");
            return ResponseEntity.ok(response);
        }
        SaveResponseDto a = new SaveResponseDto("1","1","1");
        return ResponseEntity.ok(a);
    }
}
