package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.CourseRepository;
import com.schoolmanagement.com.schoolmanagement.dao.SchoolSubjectRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    SchoolSubjectRepository schoolSubjectRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        Optional<Course> result = courseRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAllActiveCourses() {
        return courseRepository.findByIsActiveTrue();
    }

    @Override
    public List<Course> findAllByIds(List<Integer> courseIds) {
        return courseRepository.findAllById(courseIds);
    }

    @Override
    public void assignSubjectsToCourse(int courseId, List<Integer> subjectIds) {
        // Encuentra el curso por su ID
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + courseId));

        // Por cada ID de materia, busca la materia y la añade al curso si aún no está presente
        subjectIds.forEach(subjectId -> {

            SchoolSubject subject = schoolSubjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("Materia no encontrada con ID: " + subjectId));

            // Comprueba si el curso ya contiene la materia
            if (!course.getSubjects().contains(subject)) {
                course.getSubjects().add(subject);
            }
        });

        // Guarda el curso con las materias asignadas
        courseRepository.save(course);
    }

    @Override
    public List<Course> findActiveCoursesByAssistant(Integer assistantId) {
        return courseRepository.findByAssistantIdAndIsActiveTrue(assistantId);
    }


}
