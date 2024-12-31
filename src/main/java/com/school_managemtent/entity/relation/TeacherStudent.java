package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.helper.TeacherStudentKey;
import com.school_managemtent.helper.TeacherSubjectKey;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_student")
public class TeacherStudent {

    @EmbeddedId
    private TeacherStudentKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "active")
    private boolean active;

    public TeacherStudent() {
    }

    public TeacherStudent(Teacher teacher, Student student, boolean active) {
        this.teacher = teacher;
        this.student = student;
        this.active = active;
        this.id = new TeacherStudentKey(teacher.getId(), student.getId());
    }

    public TeacherStudentKey getId() {
        return id;
    }

    public void setId(TeacherStudentKey id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
