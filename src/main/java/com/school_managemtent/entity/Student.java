package com.school_managemtent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

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

    @ManyToMany(mappedBy = "students")
    private List<User> users;
}
