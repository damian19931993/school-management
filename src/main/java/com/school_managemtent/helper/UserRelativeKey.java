package com.school_managemtent.helper;

import com.school_managemtent.entity.Preceptor;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRelativeKey {

    private Long userId;
    private Long relativeId;

    public UserRelativeKey() {
    }

    public UserRelativeKey(Long userId, Long relativeId) {
        this.userId = userId;
        this.relativeId = relativeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }
}
