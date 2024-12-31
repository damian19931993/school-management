package com.school_managemtent.service;

import com.school_managemtent.dto.MarkDto;
import com.school_managemtent.dto.SaveResponseDto;

public interface MarkService {

    SaveResponseDto create(MarkDto request);
}
