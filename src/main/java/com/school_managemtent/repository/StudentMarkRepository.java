package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.StudentMark;
import com.school_managemtent.helper.StudentMarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, StudentMarkKey> {
}
