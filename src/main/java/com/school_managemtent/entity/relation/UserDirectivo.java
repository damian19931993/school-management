package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Directivo;
import com.school_managemtent.entity.User;
import com.school_managemtent.helper.UserDirectivoKey;
import jakarta.persistence.*;

@Entity
@Table(name = "user_directivo")
public class UserDirectivo {

    @EmbeddedId
    private UserDirectivoKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("directivoId")
    @JoinColumn(name = "directivo_id")
    private Directivo directivo;

    @Column(name = "active")
    private boolean active;

    public UserDirectivo(User user, Directivo directivo, boolean active) {
        this.user = user;
        this.directivo = directivo;
        this.active = active;
        this.id = new UserDirectivoKey(user.getId(), directivo.getId());
    }

    public UserDirectivoKey getId() {
        return id;
    }

    public void setId(UserDirectivoKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Directivo getDirectivo() {
        return directivo;
    }

    public void setDirectivo(Directivo directivo) {
        this.directivo = directivo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
