package com.school_managemtent.entity;

import com.school_managemtent.dto.RelativeDto;
import com.school_managemtent.entity.relation.RelativeStudent;
import com.school_managemtent.entity.relation.UserRelative;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Relative {

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
    private Date dateOfUp;
    private Date dateOfDown;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    @OneToMany(mappedBy = "relative", cascade = CascadeType.ALL)
    private List<UserRelative> userRelatives = new ArrayList<>();

    @OneToMany(mappedBy = "relative", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelativeStudent> relativeStudents = new ArrayList<>();

    public Relative() {}

    public Relative(RelativeDto dto) {
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
        this.dateOfUp = dto.getDateOfUp();
        this.dateOfDown = dto.getDateOfDown();
        this.active = true;
    }

    public void addStudentAssociation(Student student, boolean active) {
        // Buscar si ya existe un RelativeStudent con la misma PK (o el mismo Student).
        for (RelativeStudent rs : this.relativeStudents) {
            if (rs.getStudent().getId().equals(student.getId())) {
                // Ya existe la relación; solo actualiza el campo 'active' (si quieres)
                rs.setActive(active);
                return; // Evitas crear un objeto duplicado
            }
        }

        // Si no existe, creas uno nuevo
        RelativeStudent relativeStudent = new RelativeStudent(this, student, active);
        this.relativeStudents.add(relativeStudent);
        student.getRelativeStudents().add(relativeStudent);
    }


    public void removeStudentAssociation(Student student) {
        relativeStudents.removeIf(rs -> rs.getStudent().equals(student));
        student.getRelativeStudents().removeIf(rs -> rs.getRelative().equals(this));
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

    public List<RelativeStudent> getRelativeStudents() {
        return relativeStudents;
    }

    public void setRelativeStudents(List<RelativeStudent> relativeStudents) {
        this.relativeStudents = relativeStudents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
