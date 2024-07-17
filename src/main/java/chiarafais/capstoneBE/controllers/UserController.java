package chiarafais.capstoneBE.controllers;

import chiarafais.capstoneBE.entites.User;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.payloads.User.UserRegistrationResponseDTO;
import chiarafais.capstoneBE.payloads.User.UserRequiredDTO;
import chiarafais.capstoneBE.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserServices userServices;

//     1. POST http://localhost:3001/users (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO saveUtente(@RequestBody @Validated UserRequiredDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        System.out.println(body);
        return new UserRegistrationResponseDTO(this.userServices.saveUser(body).getId());}

    //     2. GET http://localhost:3001/users/me
    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    //     3. PUT http://localhost:3001/users/me
    @PutMapping("/me")
    public User updateMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.findByIdAndUpdate(currentUser.getId(), body);
    }

    //     4. GET http://localhost:3001/users
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.userServices.getUsers(page, size);
    }

    //     5. GET http://localhost:3001/users/{userId}
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable long userId) {
        return this.userServices.findById(userId);
    }


    //     6. PUT http://localhost:3001/users/{userId}
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable long userId, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.findByIdAndUpdate(userId, body);
    }

    //     7. DELETE http://localhost:3001/users/{userId}
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId) {
        this.userServices.findByIdAndDelete(userId);
    }
}

