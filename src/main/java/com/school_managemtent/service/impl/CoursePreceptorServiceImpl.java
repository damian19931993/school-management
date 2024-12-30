package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Relative;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.RelativeStudent;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.RelativeStudentKey;
import com.school_managemtent.repository.CoursePreceptorRepository;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.repository.PreceptorRepository;
import com.school_managemtent.service.CoursePreceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursePreceptorServiceImpl implements CoursePreceptorService {

    private final CourseRepository courseRepository;
    private final PreceptorRepository preceptorRepository;
    private final CoursePreceptorRepository coursePreceptorRepository;

    @Autowired
    public CoursePreceptorServiceImpl(CourseRepository courseRepository, PreceptorRepository preceptorRepository, CoursePreceptorRepository coursePreceptorRepository) {
        this.courseRepository = courseRepository;
        this.preceptorRepository = preceptorRepository;
        this.coursePreceptorRepository = coursePreceptorRepository;
    }


    @Override
    public CoursePreceptor linkCourseToPreceptor(Long courseId, Long preceptorId, boolean active) {
        var key = new CoursePreceptorKey(courseId, preceptorId);
        Optional<CoursePreceptor> existingRelation = coursePreceptorRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between course ID " + courseId + " and preceptor ID " + preceptorId + " already exists.");
        } else {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            Preceptor preceptor = preceptorRepository.findById(preceptorId)
                    .orElseThrow(() -> new RuntimeException("Preceptor not found with ID: " + preceptorId));

            CoursePreceptor cp = new CoursePreceptor(course, preceptor, active);
            coursePreceptorRepository.save(cp);

            course.getCoursePreceptors().add(cp);
            preceptor.getCoursePreceptors().add(cp);

            return cp;
        }
    }
}
