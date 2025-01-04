package com.school_managemtent.service.impl;
import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.dto.response.AllTeachersResponseDto;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.entity.log.TransactionLog;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.repository.TransactionLogRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionLogRepository transactionLogRepository;

    @Autowired
    public TeacherServiceImpl(UserRepository userRepository,
                           TeacherRepository teacherRepository,
                           PasswordEncoder passwordEncoder, TransactionLogRepository transactionLogRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Override
    public User create(TeacherDto request,String username){
        Teacher teacher = new Teacher(request);
        TransactionLog transaction = new TransactionLog();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setOperation("Crear docente.");
        transaction.setPerformedBy(username);
        transaction.setDetails("Docente creado: " + teacher.getName() + ", DNI: " + teacher.getDni());
        transactionLogRepository.save(transaction);
        return createTeacherUser(request.getEmail(), request.getPassword(), teacher);
    }

    @Override
    public AllTeachersResponseDto findAll() {
        AllTeachersResponseDto response = new AllTeachersResponseDto();
        List<TeacherDto> teachers = teacherRepository.findAll().stream()
                .map(teacher -> {
                    TeacherDto dto = new TeacherDto();
                    dto.setId(teacher.getId());
                    dto.setName(teacher.getName());
                    dto.setMiddleName1(teacher.getMiddleName1());
                    dto.setDni(teacher.getDni());
                    dto.setSituacionDeRevista(teacher.getSituacionDeRevista());
                    dto.setActive(teacher.isActive());
                    return dto;
                })
                .collect(Collectors.toList());
        response.setCode("0");
        response.setDescription("OK");
        response.setTeachers(teachers);
        return response;
    }
    @Override
    public TeacherDto findById(Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    TeacherDto dto = new TeacherDto();
                    dto.setId(teacher.getId());
                    dto.setName(teacher.getName());
                    dto.setMiddleName1(teacher.getMiddleName1());
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el docente con id: " + id));
    }

    private User createTeacherUser(String email,  String rawPassword, Teacher teacherData) {
        Teacher teacher = teacherRepository.save(teacherData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("teacher");
        user.addTeacherAssociation(teacher, true);
        return userRepository.save(user);
    }
}
