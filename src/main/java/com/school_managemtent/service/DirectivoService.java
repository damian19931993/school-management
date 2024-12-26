package com.school_managemtent.service;

import com.school_managemtent.dto.DirectivoDto;
import com.school_managemtent.entity.User;

public interface DirectivoService {

    User create(DirectivoDto request);
    DirectivoDto findById(Long id);
}
