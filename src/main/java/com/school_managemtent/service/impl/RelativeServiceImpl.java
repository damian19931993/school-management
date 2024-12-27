package com.school_managemtent.service.impl;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.RelativeDto;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Relative;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.RelativeRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RelativeServiceImpl implements RelativeService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RelativeRepository relativeRepository;

    @Autowired
    public RelativeServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RelativeRepository relativeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.relativeRepository = relativeRepository;
    }

    @Override
    public User create(RelativeDto request) {
        Relative relative = new Relative(request);
        return createRelativerUser(request.getEmail(), request.getPassword(), relative);
    }

    @Override
    public RelativeDto findById(Long id) {
        return relativeRepository.findById(id)
                .map(relative -> {
                    RelativeDto dto = new RelativeDto();
                    dto.setId(relative.getId());
                    dto.setName(relative.getName());
                    dto.setMiddleName1(relative.getMiddleName1());
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("No se encontró familiar con id: " + id));
    }

    private User createRelativerUser(String email, String rawPassword, Relative relativeData) {
        Relative relative = relativeRepository.save(relativeData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("relative");
        user.addRelativeAssociation(relative, true);
        return userRepository.save(user);
    }
}
