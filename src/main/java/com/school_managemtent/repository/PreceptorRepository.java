package com.school_managemtent.repository;

import com.school_managemtent.entity.Preceptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreceptorRepository extends JpaRepository<Preceptor,Long> {
}
