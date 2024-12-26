package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserDirectivoKey {

    private Long userId;
    private Long directivoId;

    public UserDirectivoKey() {
    }

    public UserDirectivoKey(Long userId, Long directivoId) {
        this.userId = userId;
        this.directivoId = directivoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDirectivoId() {
        return directivoId;
    }

    public void setDirectivoId(Long directivoId) {
        this.directivoId = directivoId;
    }
}
