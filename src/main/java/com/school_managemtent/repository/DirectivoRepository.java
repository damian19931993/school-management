package com.school_managemtent.repository;

import com.school_managemtent.entity.Directivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectivoRepository extends JpaRepository<Directivo, Long> {
}