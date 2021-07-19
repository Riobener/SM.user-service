package com.riobener.userservice.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.riobener.userservice.entity.User;
import com.riobener.userservice.service.UserServiceQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQuery implements GraphQLQueryResolver {
    @Autowired
    private UserServiceQL userService;

    public List<User> getUsers(int count){
        return userService.getAllUsers(count);
    }

    public Optional<User> getUser(Long id){
        return userService.getUserById(id);
    }

}
