package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.TeacherSubject;
import com.school_managemtent.helper.TeacherSubjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectKey> {

    @Query("SELECT ts.subject.id FROM TeacherSubject ts WHERE ts.teacher.id = :teacherId AND ts.active = true")
    List<Long> findSubjectIdsByTeacherId(@Param("teacherId") Long teacherId);
}
