package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.StudentRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findByDni(String dni) {
        return studentRepository.findByDni(dni);
    }
}
