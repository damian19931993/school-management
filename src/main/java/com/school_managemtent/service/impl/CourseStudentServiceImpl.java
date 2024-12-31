package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.CourseStudent;
import com.school_managemtent.helper.CourseStudentKey;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.repository.CourseStudentRepository;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseStudentServiceImpl(CourseStudentRepository courseStudentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public CourseStudent linkCourseToStudent(Long courseId, Long studentId, boolean active) {
        var key = new CourseStudentKey(courseId, studentId);
        Optional<CourseStudent> existingRelation = courseStudentRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between course ID " + courseId + " and student ID " + studentId + " already exists.");
        } else {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

            CourseStudent cs = new CourseStudent(course, student, active);
            courseStudentRepository.save(cs);

            course.getCourseStudents().add(cs);
            student.getCourseStudents().add(cs);

            return cs;
        }
    }
}
