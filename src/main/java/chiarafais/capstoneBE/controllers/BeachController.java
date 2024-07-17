package chiarafais.capstoneBE.controllers;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.payloads.Beach.BeachDTO;
import chiarafais.capstoneBE.services.BeachServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/beach")
public class BeachController {

    @Autowired
    private BeachServices beachServices;

    //     1. POST http://localhost:3001/utenti (+ body)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Beach saveBeach(@Validated @RequestBody BeachDTO body){
       return beachServices.saveNewBeach(body);
    }





}
