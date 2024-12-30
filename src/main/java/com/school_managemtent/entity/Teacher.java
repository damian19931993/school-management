package com.school_managemtent.entity;
import com.school_managemtent.dto.PreceptorDto;
import com.school_managemtent.dto.TeacherDto;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.CourseTeacher;
import com.school_managemtent.entity.relation.TeacherSubject;
import com.school_managemtent.entity.relation.UserTeacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String name;

    private String middleName1;
    private String middleName2;
    private String middleName3;

    @NotBlank(message = "El apellido es obligatorio.")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres.")
    private String surname;
    private String surname2;

    @NotBlank(message = "El dni es obligatorio.")
    @Size(max = 10, message = "El dni no puede exceder los 10 caracteres.")
    @Column(unique = true)
    private String dni;

    @NotBlank(message = "La dirección es obligatoria.")
    private String address;

    private String city;
    private String state;

    @NotNull(message = "La fecha de nacimiento no puede ser nula.")
    @Past(message = "La fechad de nacimiento debe ser en el pasado.")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String nationality;
    private String situacionDeRevista;

    @Temporal(TemporalType.DATE)
    private Date dateOfUp;

    @Temporal(TemporalType.DATE)
    private Date dateOfDown;
    private boolean active;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<UserTeacher> userTeachers = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseTeacher> courseTeachers = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherSubject> teacherSubjects = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(TeacherDto dto) {
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

    public List<UserTeacher> getUserTeachers() {
        return userTeachers;
    }

    public void setUserTeachers(List<UserTeacher> userTeachers) {
        this.userTeachers = userTeachers;
    }

    public List<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(List<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public List<TeacherSubject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }
}
