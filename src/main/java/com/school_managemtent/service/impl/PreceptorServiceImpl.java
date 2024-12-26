package com.school_managemtent.service.impl;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.PreceptorRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.PreceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PreceptorServiceImpl implements PreceptorService {

    private final UserRepository userRepository;
    private final PreceptorRepository preceptorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PreceptorServiceImpl(UserRepository userRepository, PreceptorRepository preceptorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.preceptorRepository = preceptorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(PreceptorDto request) {
        Preceptor preceptor = new Preceptor(request);
        return createPreceptorUser(request.getEmail(), request.getPassword(), preceptor);
    }

    @Override
    public PreceptorDto findById(Long id) {
        return preceptorRepository.findById(id)
                .map(preceptor -> {
                    PreceptorDto dto = new PreceptorDto();
                    dto.setId(preceptor.getId());
                    dto.setName(preceptor.getName());
                    dto.setMiddleName1(preceptor.getMiddleName1());
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el preceptor con id: " + id));
    }

    private User createPreceptorUser(String email, String rawPassword, Preceptor preceptorData) {
        Preceptor preceptor = preceptorRepository.save(preceptorData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("preceptor");
        user.addPreceptorAssociation(preceptor, true);
        return userRepository.save(user);
    }
}
