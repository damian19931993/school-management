package com.school_managemtent.dto.response;

import java.util.List;

public class AllEntityGenericResponse<T> {

    private String code;
    private String description;
    private List<T> response;

    public AllEntityGenericResponse() {}

    public AllEntityGenericResponse(String code, String description, List<T> response) {
        this.code = code;
        this.description = description;
        this.response = response;
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

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}