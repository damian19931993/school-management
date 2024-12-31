package com.school_managemtent.service.impl;

import com.school_managemtent.entity.relation.SubjectMark;
import com.school_managemtent.helper.SubjectMarkKey;
import com.school_managemtent.repository.MarkRepository;
import com.school_managemtent.repository.SubjectMarkRepository;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.SubjectMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectMarkServiceImpl implements SubjectMarkService {

    private final SubjectMarkRepository subjectMarkRepository;
    private final SubjectRepository subjectRepository;
    private final MarkRepository markRepository;

    @Autowired
    public SubjectMarkServiceImpl(SubjectMarkRepository subjectMarkRepository, SubjectRepository subjectRepository, MarkRepository markRepository) {
        this.subjectMarkRepository = subjectMarkRepository;
        this.subjectRepository = subjectRepository;
        this.markRepository = markRepository;
    }

    @Override
    public SubjectMark linkSubjectToMark(Long subjectId, Long markId, boolean active) {
        var key = new SubjectMarkKey(subjectId, markId);
        Optional<SubjectMark> existingRelation = subjectMarkRepository.findById(key);;

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between subject ID " + subjectId + " and mark ID " + markId + " already exists.");
        } else {
            var subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
            var mark = markRepository.findById(markId)
                    .orElseThrow(() -> new RuntimeException("Mark not found with ID: " + markId));

            var ss = new SubjectMark(subject, mark, active);
            subjectMarkRepository.save(ss);

            subject.getSubjectMarks().add(ss);
            mark.getSubjectMarks().add(ss);

            return ss;
        }
    }
}
