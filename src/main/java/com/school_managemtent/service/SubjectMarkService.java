package com.school_managemtent.service;

import com.school_managemtent.entity.relation.SubjectMark;

public interface SubjectMarkService {

    SubjectMark linkSubjectToMark(Long subjectId, Long markId, boolean active);
}
