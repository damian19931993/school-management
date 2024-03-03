package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.TeacherSubjectRepository;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjects;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjectsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherSubjectsServiceImpl implements TeacherSubjectsService {

    private final TeacherSubjectRepository teacherSubjectsRepository;

    @Autowired
    public TeacherSubjectsServiceImpl(TeacherSubjectRepository teacherSubjectsRepository) {
        this.teacherSubjectsRepository = teacherSubjectsRepository;
    }

    @Override
    public List<TeacherSubjects> findAll() {
        return teacherSubjectsRepository.findAll();
    }

    @Override
    public Optional<TeacherSubjects> findById(int id) {
        return teacherSubjectsRepository.findById(id);
    }

    @Override
    public TeacherSubjects findById(int teacherId, int subjectId) {
        Optional<TeacherSubjects> result = teacherSubjectsRepository.findById(new TeacherSubjectsId(teacherId, subjectId));
        TeacherSubjects teacherSubjects = null;

        if (result.isPresent()) {
            teacherSubjects = result.get();
        } else {
            // We didn't find the teacher subject relation
            throw new RuntimeException("Did not find teacher subject id - Teacher ID: " + teacherId + ", Subject ID: " + subjectId);
        }

        return teacherSubjects;
    }
    @Override
    public void save(TeacherSubjects teacherSubjects) {
        teacherSubjectsRepository.save(teacherSubjects);
    }

    @Override
    public void deleteById(int id) {
        teacherSubjectsRepository.deleteById(id);
    }

    @Override
    public void deleteAssignment(int teacherId, int subjectId) {
        // Busca la asignación por teacherId y subjectId
        // Esto asume que tienes un método en tu repositorio para hacerlo
        // Por ejemplo, podría ser un método findByTeacherIdAndSubjectId
        TeacherSubjects assignment = teacherSubjectsRepository.findByTeacherIdAndSubjectId(teacherId, subjectId);
        if (assignment != null) {
            // Si la asignación existe, elimínala
            teacherSubjectsRepository.delete(assignment);
        }
    }

    @Override
    public List<TeacherSubjects> findAssignmentsByTeacherId(int teacherId) {
        return teacherSubjectsRepository.findByTeacherId(teacherId);
    }
}
