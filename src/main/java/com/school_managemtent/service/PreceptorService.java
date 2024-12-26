package com.school_managemtent.service;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.entity.User;

public interface PreceptorService {

    User create(PreceptorDto request);
    PreceptorDto findById(Long id);
}
