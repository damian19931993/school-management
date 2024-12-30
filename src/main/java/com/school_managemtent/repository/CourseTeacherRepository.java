package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.CourseTeacher;
import com.school_managemtent.helper.CourseTeacherKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, CourseTeacherKey> {
}
