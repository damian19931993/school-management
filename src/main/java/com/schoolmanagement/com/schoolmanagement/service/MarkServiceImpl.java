package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.MarkRepository;
import com.schoolmanagement.com.schoolmanagement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRepository markRepository;


    @Override
    public void saveMark(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    public Optional<Mark> findMarkById(int id) {
        return markRepository.findById(id);
    }

    @Override
    public List<Mark> findAllMarks() {
        return markRepository.findAll();
    }

    @Override
    public void deleteMarkById(int id) {
        markRepository.deleteById(id);
    }

    @Override
    public List<MarkRepository> findActiveMarksByStudentId(int studentId) {
        return markRepository.findActiveMarksByStudentId(studentId);
    }

    @Override
    public void createOrUpdateMark(Student student, Course course, Teacher teacher, SchoolSubject subject) {
        Mark mark = new Mark();
        // Asignación directa de la entidad completa
        mark.setStudent(student);
        mark.setCourse(course);
        mark.setTeacher(teacher);
        mark.setSubject(subject);

        markRepository.save(mark);
    }

    @Override
    public void save(Mark mark) {
        markRepository.save(mark);
    }
}


