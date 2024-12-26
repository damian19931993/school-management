package com.school_managemtent.service;

import com.school_managemtent.dto.StudentDto;
import com.school_managemtent.entity.User;

public interface StudentService {

    User create(StudentDto request);
    StudentDto findById(Long id);
}
