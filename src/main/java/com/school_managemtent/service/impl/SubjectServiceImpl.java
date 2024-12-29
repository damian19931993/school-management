package com.school_managemtent.service.impl;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SaveResponseDto create(SubjectDto request) {
        Subject subject = new Subject(request);
        subjectRepository.save(subject);
        return new SaveResponseDto("0", "OK", "Materia creada con éxito.");
    }
}
