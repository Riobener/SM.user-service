package com.riobener.userservice.domain.services;

import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.EmptyUserListException;
import com.riobener.userservice.domain.model.exceptions.UserAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;
import com.riobener.userservice.infrastructure.dto.request.ChangeUserInfoDto;
import com.riobener.userservice.infrastructure.dto.request.RegisterUserDto;

import java.util.Optional;

public interface UserServiceInterface {
    User registerUser(RegisterUserDto dto) throws UserAlreadyExistException;
    User findUserById(Long id) throws UserNotFoundException;
    Iterable<User> findAllUsers() throws EmptyUserListException;
    User deleteUserById(Long id) throws UserNotFoundException;
    User changeUserInfo(ChangeUserInfoDto dto, Long userId) throws UserNotFoundException;
}
