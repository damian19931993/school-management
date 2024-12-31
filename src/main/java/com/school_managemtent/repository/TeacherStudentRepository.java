package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.TeacherStudent;
import com.school_managemtent.helper.TeacherStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentRepository extends JpaRepository<TeacherStudent, TeacherStudentKey> {
}
