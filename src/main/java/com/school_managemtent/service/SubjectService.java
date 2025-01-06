package com.school_managemtent.service;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.dto.response.AllEntityGenericResponse;

public interface SubjectService {

    SaveResponseDto create(SubjectDto request, String username);
    SubjectDto findById(Long id, String username);
    AllEntityGenericResponse<SubjectDto> findAll(String username);
}
