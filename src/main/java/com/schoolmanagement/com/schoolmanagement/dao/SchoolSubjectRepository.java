package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Integer> {
    // Aquí puedes agregar consultas específicas relacionadas con SchoolSubject
}
