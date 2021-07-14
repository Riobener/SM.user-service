package com.riobener.userservice.controller;


import com.riobener.userservice.entity.User;
import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.exception.UserNotFoundException;
import com.riobener.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Успешная регистрация");
        }catch(UserAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/user/")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestParam Long id){
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.deleteUserById(id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
