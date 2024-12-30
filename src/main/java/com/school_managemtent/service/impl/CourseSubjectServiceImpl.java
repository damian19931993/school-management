package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.CourseSubject;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.CourseSubjectKey;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.repository.CourseSubjectRepository;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.CourseSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseSubjectServiceImpl implements CourseSubjectService {

    private final CourseSubjectRepository courseSubjectRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public CourseSubjectServiceImpl(CourseSubjectRepository courseSubjectRepository, CourseRepository courseRepository, SubjectRepository subjectRepository) {
        this.courseSubjectRepository = courseSubjectRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }
    @Override
    public CourseSubject linkCourseToSubject(Long courseId, Long subjectId, boolean active) {
        var key = new CourseSubjectKey(courseId, subjectId);
        Optional<CourseSubject> existingRelation = courseSubjectRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between course ID " + courseId + " and subject ID " + subjectId + " already exists.");
        } else {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

            CourseSubject cs = new CourseSubject(course, subject, active);
            courseSubjectRepository.save(cs);

            course.getCourseSubjects().add(cs);
            subject.getCourseSubjects().add(cs);

            return cs;
        }
    }
}
