package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.StudentRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public List<Student> findAllActiveStudents() {
        return studentRepository.findByIsActive(true);
    }

    public List<Student> findStudentsByCourseId(Integer courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    @Override
    public Student findById2(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> findActiveStudentsByCourse(Course course) {
        return studentRepository.findByCoursesAndIsActive(course, true);
    }
}
