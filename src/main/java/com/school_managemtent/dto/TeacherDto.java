package com.school_managemtent.dto;

import java.util.Date;

public class TeacherDto {

    private Long id;
    private String name;
    private String email;
    private String password;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMiddleName1() {
        return middleName1;
    }

    public String getMiddleName2() {
        return middleName2;
    }

    public String getMiddleName3() {
        return middleName3;
    }

    public String getSurname() {
        return surname;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getSituacionDeRevista() {
        return situacionDeRevista;
    }

    public Date getDateOfUp() {
        return dateOfUp;
    }

    public Date getDateOfDown() {
        return dateOfDown;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMiddleName1(String middleName1) {
        this.middleName1 = middleName1;
    }

    public void setMiddleName2(String middleName2) {
        this.middleName2 = middleName2;
    }

    public void setMiddleName3(String middleName3) {
        this.middleName3 = middleName3;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setSituacionDeRevista(String situacionDeRevista) {
        this.situacionDeRevista = situacionDeRevista;
    }

    public void setDateOfUp(Date dateOfUp) {
        this.dateOfUp = dateOfUp;
    }

    public void setDateOfDown(Date dateOfDown) {
        this.dateOfDown = dateOfDown;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
