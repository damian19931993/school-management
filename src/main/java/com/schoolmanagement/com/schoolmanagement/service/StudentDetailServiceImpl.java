package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.StudentDetailRepository;
import com.schoolmanagement.com.schoolmanagement.entity.StudentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentDetailServiceImpl implements  StudentDetailService {

    @Autowired
    StudentDetailRepository studentDetailRepository;
    @Override
    public Optional<StudentDetail> findById(int id) {
        return studentDetailRepository.findById(id);
    }

    @Override
    public StudentDetail save(StudentDetail studentDetail) {
        return studentDetailRepository.save(studentDetail);
    }

    @Override
    public void deleteById(int id) {
        studentDetailRepository.deleteById(id);
    }

    @Override
    public StudentDetail findByStudentId(int id) {
        // Busca los detalles del estudiante por ID y retorna el objeto si lo encuentra
        return studentDetailRepository.findByStudentId(id)
                .orElseThrow(() -> new RuntimeException("Detalle de estudiante no encontrado para el ID: " + id));
    }
}
