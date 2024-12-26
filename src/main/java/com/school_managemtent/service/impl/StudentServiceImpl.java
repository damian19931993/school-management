package com.school_managemtent.service.impl;

import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.User;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.repository.UserRepository;
import com.school_managemtent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(UserRepository userRepository,
                              StudentRepository studentRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(StudentDto request){
        Student student = new Student();
        student.setName(request.getName());
        student.setMiddleName1(request.getMiddleName1());
        student.setMiddleName2(request.getMiddleName2());
        student.setMiddleName3(request.getMiddleName3());
        student.setSurname(request.getSurname());
        student.setSurname2(request.getSurname2());
        student.setDni(request.getDni());
        student.setAddress(request.getAddress());
        student.setCity(request.getCity());
        student.setState(request.getState());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setNationality(request.getNationality());
        student.setDateOfUp(request.getDateOfUp());
        student.setDateOfDown(request.getDateOfDown());
        student.setActive(true);
        return createStudentUser(request.getEmail(), request.getUsername(), request.getPassword(), student);
    }

    @Override
    public StudentDto findById(Long id) {
        Student student= studentRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el estudiante con id: " + id));
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setMiddleName1(student.getMiddleName1());
        return dto;
    }

    private User createStudentUser(String email, String username, String rawPassword, Student studentData) {
        Student student = studentRepository.save(studentData);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("student");
        user.addStudentAssociation(student, true);
        return userRepository.save(user);
    }
}
