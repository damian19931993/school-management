package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.PreceptorStudent;
import com.school_managemtent.helper.PreceptorStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreceptorStudentRepository extends JpaRepository<PreceptorStudent, PreceptorStudentKey> {
}
