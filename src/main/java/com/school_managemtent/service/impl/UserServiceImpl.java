package com.school_managemtent.service.impl;

import com.school_managemtent.dto.ChangePasswordRequestDto;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public boolean changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        Optional<User> userOptional = userRepository.findByUsername(changePasswordRequestDto.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())) {
                System.out.println("yeees");
                user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
