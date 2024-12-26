package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.helper.UserStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "user_student")
public class UserStudent {

    @EmbeddedId
    private UserStudentKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "active")
    private boolean active;

    public UserStudent(User user, Student student, boolean active) {
        this.user = user;
        this.student = student;
        this.active = active;
        this.id = new UserStudentKey(user.getId(), student.getId());
    }

    public UserStudentKey getId() {
        return id;
    }

    public void setId(UserStudentKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
