package com.school_managemtent.repository;

import com.school_managemtent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndRole(Long id, String role);
    List<User> findByRole(String role);
}
