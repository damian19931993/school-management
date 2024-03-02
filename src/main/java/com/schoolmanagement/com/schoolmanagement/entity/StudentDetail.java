package com.schoolmanagement.com.schoolmanagement.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "student_detail")
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(mappedBy = "studentDetail")
    private Student student;
    @Column(name = "phone1")
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    @Column(name = "phone3")
    private String phone3;

    @Column(name = "responsible1_fullname")
    private String responsible1Fullname;

    @Column(name = "responsible2_fullname")
    private String responsible2Fullname;

    @Column(name = "responsible3_fullname")
    private String responsible3Fullname;

    @Column(name = "address")
    private String address;

    @Column(name = "photo_path")
    private String photoPath;

    // Constructor vacío necesario para JPA
    public StudentDetail() {}

    // Constructor con todos los campos
    public StudentDetail(Student student, String phone1, String phone2, String phone3, String responsible1Fullname, String responsible2Fullname, String responsible3Fullname, String address, String photoPath) {
        this.student = student;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.responsible1Fullname = responsible1Fullname;
        this.responsible2Fullname = responsible2Fullname;
        this.responsible3Fullname = responsible3Fullname;
        this.address = address;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getResponsible1Fullname() {
        return responsible1Fullname;
    }

    public void setResponsible1Fullname(String responsible1Fullname) {
        this.responsible1Fullname = responsible1Fullname;
    }

    public String getResponsible2Fullname() {
        return responsible2Fullname;
    }

    public void setResponsible2Fullname(String responsible2Fullname) {
        this.responsible2Fullname = responsible2Fullname;
    }

    public String getResponsible3Fullname() {
        return responsible3Fullname;
    }

    public void setResponsible3Fullname(String responsible3Fullname) {
        this.responsible3Fullname = responsible3Fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
