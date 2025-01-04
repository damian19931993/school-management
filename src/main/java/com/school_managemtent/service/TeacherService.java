package com.school_managemtent.service;

import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.dto.response.AllTeachersResponseDto;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;

import java.util.List;

public interface TeacherService {

    User create(TeacherDto request,String username);
    TeacherDto findById(Long id);
    AllTeachersResponseDto findAll();
}
