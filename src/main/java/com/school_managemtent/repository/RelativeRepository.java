package com.school_managemtent.repository;

import com.school_managemtent.entity.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeRepository extends JpaRepository<Relative, Long> {
}
