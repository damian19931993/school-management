package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Relative;
import com.school_managemtent.entity.Student;
import com.school_managemtent.helper.RelativeStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "relative_student")
public class RelativeStudent {

    @EmbeddedId
    private RelativeStudentKey id;

    @ManyToOne
    @MapsId("relativeId")
    @JoinColumn(name = "relative_id")
    private Relative relative;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "active")
    private boolean active;

    public RelativeStudent() {}

    public RelativeStudent(Relative relative, Student student, boolean active) {
        this.relative = relative;
        this.student = student;
        this.active = active;
        this.id = new RelativeStudentKey(relative.getId(), student.getId());
    }

    public RelativeStudentKey getId() {
        return id;
    }

    public void setId(RelativeStudentKey id) {
        this.id = id;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
