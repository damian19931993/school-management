package com.school_managemtent.service.impl;

import com.school_managemtent.dto.CourseDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.entity.Course;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public SaveResponseDto create(CourseDto request) {
        Course course = new Course(request);
        courseRepository.save(course);
        return new SaveResponseDto("0", "OK", "Curso creado con éxito.");
    }
}
