package com.school_managemtent.service;

import com.school_managemtent.dto.ChangePasswordRequestDto;

public interface UserService {

    boolean changePassword(ChangePasswordRequestDto changePasswordRequestDto);
}
