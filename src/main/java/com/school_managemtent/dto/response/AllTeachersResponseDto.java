package com.school_managemtent.dto.response;

import com.school_managemtent.dto.TeacherDto;

import java.util.List;

public class AllTeachersResponseDto {

    private String code;
    private String description;
    private List<TeacherDto> teachers;

    public AllTeachersResponseDto(){}

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

    public List<TeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDto> teachers) {
        this.teachers = teachers;
    }
}
