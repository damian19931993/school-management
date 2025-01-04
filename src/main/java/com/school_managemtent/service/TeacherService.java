package com.school_managemtent.service;

import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.User;

public interface TeacherService {

    User create(TeacherDto request,String username);
    TeacherDto findById(Long id);
}
