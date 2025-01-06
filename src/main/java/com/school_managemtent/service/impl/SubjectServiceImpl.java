package com.school_managemtent.service.impl;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.dto.response.AllEntityGenericResponse;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.exception.NotFoundEntityException;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public SaveResponseDto create(SubjectDto request, String username) {
        Subject subject = new Subject(request);
        subject.setUniqueId(request.getName() + request.getCourseName() + request.getDivision() + request.getYear() + request.getShift());
        subjectRepository.save(subject);
        transactionLogService.createLog(
                "Crear materia - Éxito",
                "Materia creada: " + request.getName() + " " + request.getCourseName() + " " + request.getDivision(),
                username
        );
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

    @Override
    public AllEntityGenericResponse<SubjectDto> findAll(String username) {
        AllEntityGenericResponse<SubjectDto> response = new AllEntityGenericResponse<>();
        List<SubjectDto> subjects = subjectRepository.findAll().stream()
                .map(subject -> {
                    SubjectDto dto = new SubjectDto();
                    dto.setId(subject.getId());
                    dto.setName(subject.getName());
                    dto.setCourseName(subject.getCourseName());
                    dto.setDivision(subject.getDivision());
                    dto.setYear(subject.getYear());
                    dto.setShift(subject.getShift());
                    dto.setActive(subject.isActive());
                    return dto;
                })
                .collect(Collectors.toList());
        response.setCode("0");
        response.setDescription("OK");
        response.setResponse(subjects);
        transactionLogService.createLog(
                "Buscar todas las materias activas - Éxito",
                "Se encontraron " + subjects.size() + " materias activos.",
                username
        );
        return response;
    }
}
