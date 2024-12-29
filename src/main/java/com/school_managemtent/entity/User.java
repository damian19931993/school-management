package com.school_managemtent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school_managemtent.entity.relation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "El formato debe ser válido.")
    @Column(unique = true)
    private String email;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserTeacher> userTeachers = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserStudent> userStudents = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRelative> userRelatives = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserPreceptor> userPreceptors = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserDirectivo> userDirectivos = new ArrayList<>();


    public void addTeacherAssociation(Teacher teacher, boolean active) {
        UserTeacher userTeacher = new UserTeacher(this, teacher, active);
        this.userTeachers.add(userTeacher);
        // Opcionalmente, también lo agregas a la colección del Teacher:
        //teacher.getUserTeachers().add(userTeacher);
    }

    public void addStudentAssociation(Student student, boolean active) {
        UserStudent userStudent = new UserStudent(this, student, active);
        this.userStudents.add(userStudent);
    }

    public void addPreceptorAssociation(Preceptor preceptor, boolean active) {
        UserPreceptor userPreceptor = new UserPreceptor(this, preceptor, active);
        this.userPreceptors.add(userPreceptor);
    }

    public void addDirectivoAssociation(Directivo directivo, boolean active) {
        UserDirectivo userDirectivo = new UserDirectivo(this, directivo, active);
        this.userDirectivos.add(userDirectivo);
    }

    public void addRelativeAssociation(Relative relative, boolean active) {
        UserRelative userRelative = new UserRelative(this, relative, active);
        this.userRelatives.add(userRelative);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserTeacher> getUserTeachers() {
        return userTeachers;
    }

    public void setUserTeachers(List<UserTeacher> userTeachers) {
        this.userTeachers = userTeachers;
    }
}
