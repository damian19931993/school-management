package com.school_managemtent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany
    @JoinTable(
            name = "user_student",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

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

    public void addTeacherAssociation(Teacher teacher, boolean active) {
        UserTeacher userTeacher = new UserTeacher(this, teacher, active);
        this.userTeachers.add(userTeacher);
        // Opcionalmente, también lo agregas a la colección del Teacher:
        //teacher.getUserTeachers().add(userTeacher);
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

    public void addStudent(Student student) {
        students.add(student);
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
