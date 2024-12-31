package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubjectMarkKey {

    private Long subjectId;
    private Long markId;

    public SubjectMarkKey() {
    }

    public SubjectMarkKey(Long subjectId, Long markId) {
        this.subjectId = subjectId;
        this.markId = markId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }
}
