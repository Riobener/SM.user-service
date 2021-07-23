package com.riobener.userservice.controller;


import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.UserAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String helloUser() {
        return "Welcome to user management service Api!";
    }

    @PostMapping("/user/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            userService.registerUserModel(user);
            return ResponseEntity.ok("Успешная регистрация");
        } catch (UserAlreadyExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }



    @GetMapping("/user/")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersModels());
    }

    @GetMapping("/user")
    public ResponseEntity getUserByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
