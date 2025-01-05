package com.school_managemtent.dto.response;

public class EntityGenericResponse<T> {

    private String code;
    private String description;
    private T message;

    public EntityGenericResponse(){}

    public EntityGenericResponse(String code, String description, T message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
