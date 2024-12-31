package com.school_managemtent.entity;

import com.school_managemtent.dto.MarkDto;
import com.school_managemtent.entity.relation.StudentMark;
import com.school_managemtent.entity.relation.TeacherStudent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NotNull
    private float value;

    @NotNull
    private Date date;
    private String comments;
    private String type;

    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentMark> studentMarks = new ArrayList<>();

    public Mark() {
    }

    public Mark(String description, float value, Date date, String comments, String type) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.comments = comments;
        this.type = type;
    }

    public Mark(MarkDto dto) {
        this.description = dto.getDescription();
        this.value = dto.getValue();
        this.date = dto.getDate();
        this.comments = dto.getComments();
        this.type = dto.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StudentMark> getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(List<StudentMark> studentMarks) {
        this.studentMarks = studentMarks;
    }
}