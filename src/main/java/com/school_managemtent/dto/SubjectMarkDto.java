package com.school_managemtent.dto;

public class SubjectMarkDto {

    private Long subjectId;
    private Long markId;
    private boolean active;

    public SubjectMarkDto() {
    }

    public SubjectMarkDto(Long subjectId, Long markId) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
