package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String username);

    User saveUser(User user);

    void deleteUser (String username);

    User createUserWithAuthority(String username, String password, String authorityName);

}
