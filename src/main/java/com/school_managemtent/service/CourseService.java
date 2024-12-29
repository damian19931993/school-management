package com.school_managemtent.service;

import com.school_managemtent.dto.CourseDto;
import com.school_managemtent.dto.SaveResponseDto;

public interface CourseService {

    SaveResponseDto create(CourseDto request);
}
