package com.school_managemtent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String role;

    @ManyToMany
    @JoinTable(
            name="user_teacher",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_student",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "user_relative",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "relative_id")
    )
    private List<Relative> relatives;

    @ManyToMany
    @JoinTable(
            name = "user_preceptor",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "preceptor_id")
    )
    private List<Preceptor> preceptors;

    @ManyToMany
    @JoinTable(
            name = "user_directivo",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "directivo_id")
    )
    private List<Directivo> directivos;

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public List<Preceptor> getPreceptors() {
        return preceptors;
    }

    public void setPreceptors(List<Preceptor> preceptors) {
        this.preceptors = preceptors;
    }

    public List<Directivo> getDirectivos() {
        return directivos;
    }

    public void setDirectivos(List<Directivo> directivos) {
        this.directivos = directivos;
    }
}
