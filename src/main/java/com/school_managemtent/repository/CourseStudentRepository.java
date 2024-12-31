package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.CourseStudent;
import com.school_managemtent.helper.CourseStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentKey> {
}
