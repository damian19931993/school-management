package com.school_managemtent.service.impl;

import com.school_managemtent.dto.MarkDto;
import com.school_managemtent.dto.SaveResponseDto;
import com.school_managemtent.entity.Mark;
import com.school_managemtent.repository.MarkRepository;
import com.school_managemtent.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public SaveResponseDto create(MarkDto request) {
        Mark mark = new Mark(request);
        markRepository.save(mark);
        return new SaveResponseDto("0", "OK", "Calificación creada con éxito.");
    }
}
