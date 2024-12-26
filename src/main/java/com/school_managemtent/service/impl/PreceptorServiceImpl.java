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
        Preceptor preceptor = new Preceptor();
        preceptor.setName(request.getName());
        preceptor.setMiddleName1(request.getMiddleName1());
        preceptor.setMiddleName2(request.getMiddleName2());
        preceptor.setMiddleName3(request.getMiddleName3());
        preceptor.setSurname(request.getSurname());
        preceptor.setSurname2(request.getSurname2());
        preceptor.setDni(request.getDni());
        preceptor.setAddress(request.getAddress());
        preceptor.setCity(request.getCity());
        preceptor.setState(request.getState());
        preceptor.setDateOfBirth(request.getDateOfBirth());
        preceptor.setNationality(request.getNationality());
        preceptor.setSituacionDeRevista(request.getSituacionDeRevista());
        preceptor.setDateOfUp(request.getDateOfUp());
        preceptor.setDateOfDown(request.getDateOfDown());
        preceptor.setActive(true);
        return createPreceptorUser(request.getEmail(), request.getUsername(), request.getPassword(), preceptor);
    }

    @Override
    public PreceptorDto findById(Long id) {
        Preceptor preceptor = preceptorRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el preceptor con id: " + id));
        PreceptorDto dto = new PreceptorDto();
        dto.setId(preceptor.getId());
        dto.setName(preceptor.getName());
        dto.setMiddleName1(preceptor.getMiddleName1());
        return dto;
    }

    private User createPreceptorUser(String email, String username, String rawPassword, Preceptor preceptorData) {
        Preceptor preceptor = preceptorRepository.save(preceptorData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("preceptor");
        user.addPrecptorAssociation(preceptor, true);
        return userRepository.save(user);
    }
}
