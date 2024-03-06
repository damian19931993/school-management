package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.MarkRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Mark;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkServiceImpl implements MarkService {

    private MarkRepository markRepository;


    @Override
    public Mark saveMark(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public Optional<Mark> findMarkById(int id) {
        return markRepository.findById(id);
    }

    @Override
    public List<Mark> findAllMarks() {
        return markRepository.findAll();
    }

    @Override
    public void deleteMarkById(int id) {
        markRepository.deleteById(id);
    }

    @Override
    public List<MarkRepository> findActiveMarksByStudentId(int studentId) {
        return markRepository.findActiveMarksByStudentId(studentId);
    }
}


