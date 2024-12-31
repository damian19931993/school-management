package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.TeacherMark;
import com.school_managemtent.helper.TeacherMarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMarkRepository extends JpaRepository<TeacherMark, TeacherMarkKey> {
}
