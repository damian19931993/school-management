package com.school_managemtent.entity;

import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.entity.relation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Preceptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String middleName1;
    private String middleName2;
    private String middleName3;
    private String surname;
    private String surname2;
    private String dni;
    private String address;
    private String city;
    private String state;
    private Date dateOfBirth;
    private String nationality;
    private String situacionDeRevista;
    private Date dateOfUp;
    private Date dateOfDown;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    @OneToMany(mappedBy = "preceptor", cascade = CascadeType.ALL)
    private List<UserPreceptor> userPreceptors = new ArrayList<>();

    @OneToMany(mappedBy = "preceptor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePreceptor> coursePreceptors = new ArrayList<>();

    @OneToMany(mappedBy = "preceptor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreceptorStudent> preceptorStudents = new ArrayList<>();

    public Preceptor() {
    }

    public Preceptor(PreceptorDto dto) {
            this.name = dto.getName();
            this.middleName1 = dto.getMiddleName1();
            this.middleName2 = dto.getMiddleName2();
            this.middleName3 = dto.getMiddleName3();
            this.surname = dto.getSurname();
            this.surname2 = dto.getSurname2();
            this.dni = dto.getDni();
            this.address = dto.getAddress();
            this.city = dto.getCity();
            this.state = dto.getState();
            this.dateOfBirth = dto.getDateOfBirth();
            this.nationality = dto.getNationality();
            this.situacionDeRevista = dto.getSituacionDeRevista();
            this.dateOfUp = dto.getDateOfUp();
            this.dateOfDown = dto.getDateOfDown();
            this.active = true;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName1() {
        return middleName1;
    }

    public void setMiddleName1(String middleName1) {
        this.middleName1 = middleName1;
    }

    public String getMiddleName2() {
        return middleName2;
    }

    public void setMiddleName2(String middleName2) {
        this.middleName2 = middleName2;
    }

    public String getMiddleName3() {
        return middleName3;
    }

    public void setMiddleName3(String middleName3) {
        this.middleName3 = middleName3;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSituacionDeRevista() {
        return situacionDeRevista;
    }

    public void setSituacionDeRevista(String situacionDeRevista) {
        this.situacionDeRevista = situacionDeRevista;
    }

    public Date getDateOfUp() {
        return dateOfUp;
    }

    public void setDateOfUp(Date dateOfUp) {
        this.dateOfUp = dateOfUp;
    }

    public Date getDateOfDown() {
        return dateOfDown;
    }

    public void setDateOfDown(Date dateOfDown) {
        this.dateOfDown = dateOfDown;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UserPreceptor> getUserPreceptors() {
        return userPreceptors;
    }

    public void setUserPreceptors(List<UserPreceptor> userPreceptors) {
        this.userPreceptors = userPreceptors;
    }

    public List<CoursePreceptor> getCoursePreceptors() {
        return coursePreceptors;
    }

    public void setCoursePreceptors(List<CoursePreceptor> coursePreceptors) {
        this.coursePreceptors = coursePreceptors;
    }

    public List<PreceptorStudent> getPreceptorStudents() {
        return preceptorStudents;
    }

    public void setPreceptorStudents(List<PreceptorStudent> preceptorStudents) {
        this.preceptorStudents = preceptorStudents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
