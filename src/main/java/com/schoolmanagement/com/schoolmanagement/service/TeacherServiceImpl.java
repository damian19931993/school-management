package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.TeacherRepository;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {


    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }




    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int theId) {
        Optional<Teacher> result = teacherRepository.findById(theId);
        Teacher theTeacher = null;

        if (result.isPresent()) {
            theTeacher = result.get();
        } else {
            // We didn't find the teacher
            throw new RuntimeException("Did not find teacher id - " + theId);
        }

        return theTeacher;
    }

    @Override
    public void save(Teacher theTeacher) {
        teacherRepository.save(theTeacher);
    }

    @Override
    public void deleteById(int theId) {
        teacherRepository.deleteById(theId);
    }

    @Override
    public List<Teacher> findAllActiveTeachers() {
        return teacherRepository.findAllByIsActiveTrue();
    }

    @Override
    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username).orElse(null);
    }


}

