package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.helper.CoursePreceptorKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePreceptorRepository extends JpaRepository<CoursePreceptor, CoursePreceptorKey> {
}
