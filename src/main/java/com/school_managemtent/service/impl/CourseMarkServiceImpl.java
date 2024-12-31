package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Mark;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.relation.CourseMark;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.helper.CourseMarkKey;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.repository.CourseMarkRepository;
import com.school_managemtent.repository.CourseRepository;
import com.school_managemtent.repository.MarkRepository;
import com.school_managemtent.service.CourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseMarkServiceImpl implements CourseMarkService {

    private final CourseMarkRepository courseMarkRepository;
    private final CourseRepository courseRepository;
    private final MarkRepository markRepository;

    @Autowired
    public CourseMarkServiceImpl(CourseMarkRepository courseMarkRepository, CourseRepository courseRepository, MarkRepository markRepository) {
        this.courseMarkRepository = courseMarkRepository;
        this.courseRepository = courseRepository;
        this.markRepository = markRepository;
    }

    @Override
    public CourseMark linkCourseToMark(Long courseId, Long markId, boolean active) {
        var key = new CourseMarkKey(courseId, markId);
        Optional<CourseMark> existingRelation = courseMarkRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between course ID " + courseId + " and mark ID " + markId + " already exists.");
        } else {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            Mark mark = markRepository.findById(markId)
                    .orElseThrow(() -> new RuntimeException("Mark not found with ID: " + markId));

            CourseMark cp = new CourseMark(course, mark, active);
            courseMarkRepository.save(cp);

            course.getCourseMarks().add(cp);
            mark.getCourseMarks().add(cp);

            return cp;
        }
    }
}
