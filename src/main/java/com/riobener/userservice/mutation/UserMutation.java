package com.riobener.userservice.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.service.UserServiceQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    @Autowired
    private UserServiceQL userService;

    public User registerUser(String username, String password) throws UserAlreadyExistException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.registerUserQL(user);
    }
}
