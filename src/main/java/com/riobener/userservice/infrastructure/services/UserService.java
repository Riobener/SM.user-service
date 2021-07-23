package com.riobener.userservice.infrastructure.services;

import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.services.UserServiceInterface;
import com.riobener.userservice.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;
    @Override
    public void registerUser(User user) {

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void changeUserInfo(Long id) {

    }
}
