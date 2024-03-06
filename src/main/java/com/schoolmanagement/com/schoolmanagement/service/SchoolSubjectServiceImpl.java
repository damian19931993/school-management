package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.SchoolSubjectRepository;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolSubjectServiceImpl implements SchoolSubjectService {


    private SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public SchoolSubjectServiceImpl(SchoolSubjectRepository theSchoolSubjectRepository) {
        schoolSubjectRepository = theSchoolSubjectRepository;
    }

    @Override
    public List<SchoolSubject> findAll() {
        return schoolSubjectRepository.findAll();
    }

    @Override
    public SchoolSubject findById(int theId) {
        Optional<SchoolSubject> result = schoolSubjectRepository.findById(theId);
        SchoolSubject theSchoolSubject = null;

        if (result.isPresent()) {
            theSchoolSubject = result.get();
        }
        else {
            // we didn't find the school subject
            throw new RuntimeException("Did not find school subject id - " + theId);
        }

        return theSchoolSubject;
    }

    @Override
    public void save(SchoolSubject theSchoolSubject) {
        schoolSubjectRepository.save(theSchoolSubject);
    }

    @Override
    public void deleteById(int theId) {
        schoolSubjectRepository.deleteById(theId);
    }

    @Override
    public List<SchoolSubject> findActiveSubjectsByCourse(int courseId) {
        // Asumiendo que el campo que indica si una materia está activa se llama 'isActive' y es de tipo Boolean
        return schoolSubjectRepository.findByCourseIdAndIsActive(courseId, true);
    }
}


