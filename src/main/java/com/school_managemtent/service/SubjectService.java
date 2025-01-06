package com.school_managemtent.service;

import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.dto.SubjectDto;

public interface SubjectService {

    SaveResponseDto create(SubjectDto request, String username);
    SubjectDto findById(Long id, String username);
}
