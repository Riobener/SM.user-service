package com.riobener.userservice.infrastructure.repositories;

import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.repositories.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryInterface {
    @Autowired
    UserJpaRepository userJPARepository;
    @Override
    public User save(User user) {
        return userJPARepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJPARepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJPARepository.findByUsername(username);
    }

    @Override
    public Iterable<User> findAll() {
        return userJPARepository.findAll();
    }


    @Override
    public void delete(Long id) {
        userJPARepository.deleteById(id);
    }
}
