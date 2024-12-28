package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.RelativeStudent;
import com.school_managemtent.helper.RelativeStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeStudentRepository extends JpaRepository<RelativeStudent, RelativeStudentKey> {
}
