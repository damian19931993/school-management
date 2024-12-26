package com.school_managemtent.service.impl;

import com.school_managemtent.dto.DirectivoDto;
import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.entity.Directivo;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.DirectivoRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.DirectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DirectivoServiceImpl implements DirectivoService {

    private final UserRepository userRepository;
    private final DirectivoRepository directivoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DirectivoServiceImpl(UserRepository userRepository, DirectivoRepository directivoRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.directivoRepository = directivoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(DirectivoDto request) {
        Directivo directivo = new Directivo(request);
        return createDirectivorUser(request.getEmail(), request.getPassword(), directivo);
    }

    @Override
    public DirectivoDto findById(Long id) {
        return directivoRepository.findById(id)
                .map(directivo -> {
                    DirectivoDto dto = new DirectivoDto();
                    dto.setId(directivo.getId());
                    dto.setName(directivo.getName());
                    dto.setMiddleName1(directivo.getMiddleName1());
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el directivo con id: " + id));
    }

    private User createDirectivorUser(String email, String rawPassword, Directivo directivoData) {
        Directivo directivo = directivoRepository.save(directivoData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("DIRECTIVO");
        user.addDirectivoAssociation(directivo, true);
        return userRepository.save(user);
    }
}
