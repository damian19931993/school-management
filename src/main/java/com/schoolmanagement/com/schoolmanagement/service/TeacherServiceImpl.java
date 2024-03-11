package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.CourseRepository;
import com.schoolmanagement.com.schoolmanagement.dao.TeacherRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    CourseRepository courseRepository;
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

    @Override
    public int findTeacherIdByPrincipal(Principal principal) {
        Teacher teacher = findByUsername(principal.getName());
        if (teacher != null) {
            return teacher.getId();
        } else {
            // Lanza una excepción o maneja el caso de que el profesor no se encuentre
            throw new RuntimeException("Teacher not found");
        }
    }

    @Override
    public List<Course> findAllActiveCourses(Teacher teacher) {
        return courseRepository.findAllActiveByTeacher(teacher.getId());
    }
}

