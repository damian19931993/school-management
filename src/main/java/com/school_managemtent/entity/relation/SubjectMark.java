package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Mark;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.helper.StudentMarkKey;
import com.school_managemtent.helper.SubjectMarkKey;
import jakarta.persistence.*;

@Entity
@Table(name = "subject_mark")
public class SubjectMark {

    @EmbeddedId
    private SubjectMarkKey id;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @MapsId("markId")
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @Column(name = "active")
    private boolean active;

    public SubjectMark() {
    }

    public SubjectMark(Subject subject, Mark mark, boolean active) {
        this.subject = subject;
        this.mark = mark;
        this.active = active;
        this.id = new SubjectMarkKey(subject.getId(), mark.getId());
    }

    public SubjectMarkKey getId() {
        return id;
    }

    public void setId(SubjectMarkKey id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
