package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserPreceptorKey {

    private Long userId;
    private Long preceptorId;

    public UserPreceptorKey() {
    }

    public UserPreceptorKey(Long userId, Long preceptorId) {
        this.userId = userId;
        this.preceptorId = preceptorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPreceptorId() {
        return preceptorId;
    }

    public void setPreceptorId(Long preceptorId) {
        this.preceptorId = preceptorId;
    }
}
