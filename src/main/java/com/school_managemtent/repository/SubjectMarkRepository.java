package com.school_managemtent.repository;

import com.school_managemtent.entity.relation.SubjectMark;
import com.school_managemtent.helper.SubjectMarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectMarkRepository extends JpaRepository<SubjectMark, SubjectMarkKey> {

}
