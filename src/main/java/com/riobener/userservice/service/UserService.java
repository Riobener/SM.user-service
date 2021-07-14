package com.riobener.userservice.service;

import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.exception.UserNotFoundException;
import com.riobener.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws UserAlreadyExistException {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public User deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Удаляемый пользователь не найден");
        }else{
            userRepository.deleteById(user.getId());

        }
        return user;
    }

}
