package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.CourseSubject;
import com.school_managemtent.helper.CourseSubjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, CourseSubjectKey> {
}
