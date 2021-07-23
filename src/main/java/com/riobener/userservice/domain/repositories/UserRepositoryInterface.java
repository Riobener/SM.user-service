package com.riobener.userservice.domain.repositories;

import com.riobener.userservice.domain.model.entities.User;

import java.util.Optional;

public interface UserRepositoryInterface {
    User save(User user);

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Iterable<User> findAll();
    void delete(Long id);
}
