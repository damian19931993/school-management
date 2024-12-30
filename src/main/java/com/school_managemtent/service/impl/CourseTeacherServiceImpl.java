package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.CourseTeacher;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.CourseTeacherKey;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.repository.CourseTeacherRepository;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseTeacherRepository courseTeacherRepository;

    @Autowired
    public CourseTeacherServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, CourseTeacherRepository courseTeacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.courseTeacherRepository = courseTeacherRepository;
    }

    @Override
    public CourseTeacher linkCourseToTeacher(Long courseId, Long teacherId, boolean active) {
        var key = new CourseTeacherKey(courseId, teacherId);
        Optional<CourseTeacher> existingRelation = courseTeacherRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between course ID " + courseId + " and teacher ID " + teacherId + " already exists.");
        } else {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));

            CourseTeacher ct = new CourseTeacher(course, teacher, active);
            courseTeacherRepository.save(ct);

            course.getCourseTeachers().add(ct);
            teacher.getCourseTeachers().add(ct);

            return ct;
        }
    }
}
