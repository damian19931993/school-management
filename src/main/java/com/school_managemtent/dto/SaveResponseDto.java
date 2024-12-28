package com.school_managemtent.dto;

public class SaveResponseDto {

    private String code;
    private String description;
    private String message;

    public SaveResponseDto() {}

    public SaveResponseDto(String code, String description, String message) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
