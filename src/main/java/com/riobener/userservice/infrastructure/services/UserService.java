package com.riobener.userservice.infrastructure.services;

import com.google.common.collect.Iterables;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.EmptyUserListException;
import com.riobener.userservice.domain.model.exceptions.UserAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;
import com.riobener.userservice.domain.model.value_objects.UserInfo;
import com.riobener.userservice.domain.services.UserServiceInterface;
import com.riobener.userservice.infrastructure.dto.request.ChangeUserInfoDto;
import com.riobener.userservice.infrastructure.dto.request.RegisterUserDto;
import com.riobener.userservice.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(RegisterUserDto dto) throws UserAlreadyExistException {
        boolean userExists = userRepository.findByUsername(dto.getUsername()).isPresent();
        if (userExists) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        User user = new User(dto.getUsername(),
                encodedPassword,
                new UserInfo(dto.getInfo().getFirstName(), dto.getInfo().getLastName()),
                dto.getUserRole()
        );
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException {
        boolean userExists = userRepository.findById(id).isPresent();
        if (!userExists) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public Iterable<User> findAllUsers() throws EmptyUserListException {
        Iterable<User> users = userRepository.findAll();
        if (Iterables.size(users) == 0) {
            throw new EmptyUserListException("Список пользователей пуст");
        }
        return users;
    }

    @Override
    public User deleteUserById(Long id) throws UserNotFoundException {
        boolean userExists = userRepository.findById(id).isPresent();
        if (!userExists) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        User user = userRepository.findById(id).get();
        userRepository.delete(id);
        return user;
    }

    @Override
    public User changeUserInfo(ChangeUserInfoDto dto, Long userId) throws UserNotFoundException {
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        User user = userRepository.findById(userId).get();
        user.setInfo(new UserInfo(dto.getUserInfoDto().getFirstName(), dto.getUserInfoDto().getLastName()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean userExists = userRepository.findByUsername(username).isPresent();

        if (!userExists) {
            return null;
        }
        User user = userRepository.findByUsername(username).get();
        return user;
    }
}
