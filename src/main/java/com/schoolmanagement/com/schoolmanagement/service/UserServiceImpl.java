package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.AuthorityRepository;
import com.schoolmanagement.com.schoolmanagement.dao.UserRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Authority;
import com.schoolmanagement.com.schoolmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User createUserWithAuthority(String username, String password, String authorityName) {
        Authority authority = authorityRepository.findByAuthority(authorityName);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        // Suponiendo que tienes un método setRole o similar en tu entidad User
        newUser.setAuthority(authority);
        return userRepository.save(newUser);
    }
}
