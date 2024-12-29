package com.school_managemtent.entity;

import com.school_managemtent.dto.CourseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String division;
    private String year;
    private String shift;
    private boolean active;

    public Course() {
    }

    public Course(String name, String division, String year, String shift, boolean active) {
        this.name = name;
        this.division = division;
        this.year = year;
        this.shift = shift;
        this.active = active;
    }

    public Course(CourseDto request){
        this.name = request.getName();
        this.division = request.getDivision();
        this.year = request.getYear();
        this.shift = request.getShift();
        this.active = request.isActive();
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

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
