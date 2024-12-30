package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.TeacherSubject;
import com.school_managemtent.helper.TeacherSubjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectKey> {
}
