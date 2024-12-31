package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.CourseMark;
import com.school_managemtent.helper.CourseMarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMarkRepository extends JpaRepository<CourseMark, CourseMarkKey> {
}
