package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.StudentSubject;
import com.school_managemtent.helper.StudentSubjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectKey> {
}
