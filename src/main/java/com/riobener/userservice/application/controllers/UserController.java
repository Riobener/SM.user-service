package com.riobener.userservice.application.controllers;


import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.domain.model.exceptions.EmptyUserListException;
import com.riobener.userservice.domain.model.exceptions.UserAlreadyExistException;
import com.riobener.userservice.domain.model.exceptions.UserNotFoundException;

import com.riobener.userservice.infrastructure.dto.request.AuthenticationRequest;
import com.riobener.userservice.infrastructure.dto.request.ChangeUserInfoDto;
import com.riobener.userservice.infrastructure.dto.request.RegisterUserDto;
import com.riobener.userservice.infrastructure.dto.response.AuthenticationResponse;
import com.riobener.userservice.infrastructure.dto.response.UserResponseDto;
import com.riobener.userservice.infrastructure.security.jwt.JwtUtil;
import com.riobener.userservice.infrastructure.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String helloUser() {
        return "Welcome to user management service Api!";
    }


    @PostMapping("/user/register")
    @ApiOperation(value = "Регистрирует пользователя по данным из тела запроса", response = UserResponseDto.class)
    public ResponseEntity registerUser(@RequestBody RegisterUserDto dto) {
        try {
            UserResponseDto userResponseDto = modelMapper.map(userService.registerUser(dto), UserResponseDto.class);
            return ResponseEntity.ok(userResponseDto);
        } catch (UserAlreadyExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/user/")
    @ApiOperation(value = "Возвращает всех существующих пользователей", response = UserResponseDto.class)
    public ResponseEntity getAllUsers() {
        try {
            Iterable<User> users = userService.findAllUsers();
            return ResponseEntity.ok(StreamSupport.stream(users.spliterator(), false).
                    map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList()));
        } catch (EmptyUserListException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "Возвращает пользователя по личному идентификатору", response = UserResponseDto.class)
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            UserResponseDto userResponseDto = modelMapper.map(userService.findUserById(id), UserResponseDto.class);
            return ResponseEntity.ok(userResponseDto);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/user/{id}")
    @ApiOperation(value = "Изменяет информацию о пользователе по данным из тела запроса", response = UserResponseDto.class)
    public ResponseEntity changeUserInfo(@RequestBody ChangeUserInfoDto dto, @PathVariable Long id) {
        try {
            UserResponseDto userResponseDto = modelMapper.map(userService.changeUserInfo(dto, id), UserResponseDto.class);
            return ResponseEntity.ok(userResponseDto);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "Удаляет пользователя по личному идентификатору", response = UserResponseDto.class)
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            UserResponseDto userResponseDto = modelMapper.map(userService.deleteUserById(id), UserResponseDto.class);
            return ResponseEntity.ok(userResponseDto);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/user/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}


