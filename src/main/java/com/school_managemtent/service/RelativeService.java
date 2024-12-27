package com.school_managemtent.service;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.RelativeDto;
import com.school_managemtent.entity.User;

public interface RelativeService {

    User create(RelativeDto request);
    RelativeDto findById(Long id);
}
