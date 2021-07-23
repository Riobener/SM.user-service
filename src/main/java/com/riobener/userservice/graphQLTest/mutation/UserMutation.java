/*
package com.riobener.userservice.graphQLTest.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.UserAlreadyExistException;
import com.riobener.userservice.graphQLTest.service.UserServiceQL;
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
*/
