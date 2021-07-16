package com.riobener.userservice.registration;

import com.riobener.userservice.entity.User;
import com.riobener.userservice.entity.UserRole;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.model.UserModel;
import com.riobener.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public UserModel register(RegistrationRequest request) throws UserAlreadyExistException {
        System.out.println(request.getPassword());
        System.out.println(request.getUsername());
        //paste all logic here
        return userService.registerUser(new User(
                request.getUsername(),
                request.getPassword(),
                UserRole.USER
        ));
    }
}
