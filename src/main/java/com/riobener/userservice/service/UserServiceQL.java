package com.riobener.userservice.service;

import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceQL implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
