package chiarafais.capstoneBE.controllers;

import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.payloads.User.UserLoginDTO;
import chiarafais.capstoneBE.payloads.User.UserLoginResponseDTO;
import chiarafais.capstoneBE.payloads.User.UserRegistrationResponseDTO;
import chiarafais.capstoneBE.payloads.User.UserRequiredDTO;
import chiarafais.capstoneBE.services.AuthServices;
import chiarafais.capstoneBE.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(authServices.generateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO registerUser(@RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRegistrationResponseDTO(this.userServices.saveUser(body).getId());
    }
}
