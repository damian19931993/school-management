package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.User;
import com.school_managemtent.helper.UserTeacherKey;
import jakarta.persistence.*;

@Entity
@Table(name = "user_teacher")
public class UserTeacher {

    @EmbeddedId
    private UserTeacherKey id;

    @ManyToOne
    @MapsId("userId")   // Mapeamos la parte userId de la PK
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("teacherId") // Mapeamos la parte teacherId de la PK
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "active")
    private boolean active;

    public UserTeacher() {}

    public UserTeacher(User user, Teacher teacher, boolean active) {
        this.user = user;
        this.teacher = teacher;
        this.active = active;
        // Creamos la PK compuesta
        this.id = new UserTeacherKey(user.getId(), teacher.getId());
    }

    public UserTeacherKey getId() {
        return id;
    }

    public void setId(UserTeacherKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
