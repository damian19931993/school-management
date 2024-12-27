package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Relative;
import com.school_managemtent.entity.User;
import com.school_managemtent.helper.UserPreceptorKey;
import com.school_managemtent.helper.UserRelativeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "user_relative")
public class UserRelative {

    @EmbeddedId
    private UserRelativeKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("relativeId")
    @JoinColumn(name = "relative_id")
    private Relative relative;

    @Column(name = "active")
    private boolean active;

    public UserRelative(User user, Relative relative, boolean active) {
        this.user = user;
        this.relative = relative;
        this.active = active;
        this.id = new UserRelativeKey(user.getId(), relative.getId());
    }

    public UserRelativeKey getId() {
        return id;
    }

    public void setId(UserRelativeKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
