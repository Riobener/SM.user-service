package com.riobener.userservice.registration;

import com.riobener.userservice.exception.UserAlreadyExistException;
import com.riobener.userservice.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationRequest request) throws UserAlreadyExistException {
        try{
            return ResponseEntity.ok(registrationService.register(request));
        }catch(UserAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
