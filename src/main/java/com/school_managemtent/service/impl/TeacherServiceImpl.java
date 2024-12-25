package com.school_managemtent.service.impl;

import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImpl(UserRepository userRepository,
                           TeacherRepository teacherRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(TeacherDto request){
        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setMiddleName1(request.getMiddleName1());
        teacher.setMiddleName2(request.getMiddleName2());
        teacher.setMiddleName3(request.getMiddleName3());
        teacher.setSurname(request.getSurname());
        teacher.setSurname2(request.getSurname2());
        teacher.setDni(request.getDni());
        teacher.setAddress(request.getAddress());
        teacher.setCity(request.getCity());
        teacher.setState(request.getState());
        teacher.setDateOfBirth(request.getDateOfBirth());
        teacher.setNationality(request.getNationality());
        teacher.setSituacionDeRevista(request.getSituacionDeRevista());
        teacher.setDateOfUp(request.getDateOfUp());
        teacher.setDateOfDown(request.getDateOfDown());
        teacher.setActive(true);
        return createTeacherUser(request.getEmail(), request.getPassword(), teacher);
    }

    @Override
    public TeacherDto findById(Long id) {
        Teacher teacher= teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el teacher con id: " + id));
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setMiddleName1(teacher.getMiddleName1());
        return dto;
    }

    private User createTeacherUser(String email, String rawPassword, Teacher teacherData) {
        Teacher teacher = teacherRepository.save(teacherData);
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("teacher");
        user.addTeacher(teacher);
        return userRepository.save(user);
    }
}
