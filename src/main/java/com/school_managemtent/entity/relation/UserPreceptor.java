package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.User;
import com.school_managemtent.helper.UserPreceptorKey;
import com.school_managemtent.helper.UserStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "user_preceptor")
public class UserPreceptor {

    @EmbeddedId
    private UserPreceptorKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("preceptorId")
    @JoinColumn(name = "preceptor_id")
    private Preceptor preceptor;

    @Column(name = "active")
    private boolean active;

    public UserPreceptor(User user, Preceptor preceptor, boolean active) {
        this.user = user;
        this.preceptor = preceptor;
        this.active = active;
        this.id = new UserPreceptorKey(user.getId(), preceptor.getId());
    }

    public UserPreceptorKey getId() {
        return id;
    }

    public void setId(UserPreceptorKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Preceptor getPreceptor() {
        return preceptor;
    }

    public void setPreceptor(Preceptor preceptor) {
        this.preceptor = preceptor;
    }
}

