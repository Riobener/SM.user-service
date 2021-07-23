package com.riobener.userservice.domain.services;

import com.riobener.userservice.domain.model.entities.User;

import java.util.Optional;

public interface UserServiceInterface {
    void registerUser(User user);
    Optional<User> findByUsername(String username);
    Iterable<User> findAllUsers();
    void deleteUser(User user);
    void changeUserInfo(Long id);
}
