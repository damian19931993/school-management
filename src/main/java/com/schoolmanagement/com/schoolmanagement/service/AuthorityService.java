package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Authority;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {
    List<Authority> findAll();

     Optional<Authority> findById(int id) ;

    Authority save(Authority authority);

    void deleteById(int id);


}
