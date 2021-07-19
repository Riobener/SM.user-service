package com.riobener.userservice.service;

import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.exception.UserNotFoundException;
import com.riobener.userservice.model.UserModel;
import com.riobener.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel registerUserModel(User user) throws UserAlreadyExistException {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            throw new UserAlreadyExistException("already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return UserModel.toModel(userRepository.save(user));
    }

    public List<UserModel> getAllUsersModels() {
        return UserModel.toListModels(userRepository.findAll());
    }
    //GraphQL
    public User registerUserQL(User user) throws UserAlreadyExistException {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            throw new UserAlreadyExistException("already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    //GraphQL
    public List<User> getAllUsers(int count) {
        return userRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }
    //GraphQL
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public UserModel getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserModel.toModel(user);
    }

    public UserModel deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Удаляемый пользователь не найден");
        }else{
            userRepository.deleteById(user.getId());
        }
        return UserModel.toModel(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }


}
