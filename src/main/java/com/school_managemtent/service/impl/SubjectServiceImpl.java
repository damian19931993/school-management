package com.school_managemtent.service.impl;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.exception.NotFoundEntityException;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TransactionLogService transactionLogService;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, TransactionLogService transactionLogService) {
        this.subjectRepository = subjectRepository;
        this.transactionLogService = transactionLogService;
    }

    @Override
    public SaveResponseDto create(SubjectDto request) {
        Subject subject = new Subject(request);
        subjectRepository.save(subject);
        return new SaveResponseDto("0", "OK", "Materia creada con éxito.");
    }

    @Override
    public SubjectDto findById(Long id, String username) {
        transactionLogService.createLog(
                "Buscar materia - Éxito",
                "Se encontro materia con id " + id,
                username
        );
        return subjectRepository.findById(id)
                .map(subject -> {
                    SubjectDto dto = new SubjectDto();
                    dto.setName(subject.getName());
                    dto.setCourseName(subject.getCourseName());
                    dto.setDivision(subject.getDivision());
                    dto.setYear(subject.getYear());
                    dto.setShift(subject.getShift());
                    dto.setActive(true);
                    return dto;
                })
                .orElseThrow(() -> new NotFoundEntityException("No se encontró la materia con id: " + id, username));
    }
}
