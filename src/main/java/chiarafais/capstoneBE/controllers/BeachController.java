package chiarafais.capstoneBE.controllers;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.payloads.Beach.BeachDTO;
import chiarafais.capstoneBE.services.BeachServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/beach")
public class BeachController {

    @Autowired
    private BeachServices beachServices;

    //     1. POST http://localhost:3001/beach (+ body)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Beach saveBeach(@Validated @RequestBody BeachDTO body){
       return beachServices.saveNewBeach(body);
    }

    // 2.GET http://localhost:3001/beach ritorna la lista delle spiagge
    @GetMapping
    public Page<Beach> findAllBeach(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return beachServices.findAll(page,size);
    }

    // 3. GET http://localhost:3001/beach/{id_spiaggia} ritorna la specifica spiaggia
    @GetMapping("/{id_spiaggia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Beach findById(@PathVariable long id_spiaggia){return beachServices.findById(id_spiaggia);}


    // 4. PUT http://localhost:3001/beach/{id_spiaggia} + body modifica della specifica spiaggia
    @PutMapping("/{id_spiaggia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Beach updateBeach(@PathVariable long id_spiaggia, @Validated @RequestBody BeachDTO body, BindingResult result){
        if(result.hasErrors()){
            throw new BadRequestException(result.getAllErrors());
        }
        return beachServices.updateBeach(id_spiaggia, body);
    }


    // 5. DELETE http://localhost:3001/beach/{id_spiaggia} elimina la specifica spiaggia
    @DeleteMapping("/{id_spiaggia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeach(@PathVariable long id_spiaggia){
      beachServices.deleteBeach(id_spiaggia);
    }

    // 6. GET http://localhost:3001/beach/establishment
    @GetMapping("/establishment")
    public List<Beach> establishment(){
        return beachServices.establishmentTrue();
    }

    // 7. GET http://localhost:3001/beach/available
    @GetMapping("/available")
    public List<Beach> available(){
        return beachServices.available();
    }

    // 8. GET http://localhost:3001/beach/province{nome provincia}
    @GetMapping("/province/{provinces}")
    public List<Beach> province(@PathVariable String provinces){
        return beachServices.filterProvince(provinces);
    }

    // 9. GET http://localhost:3001/beach/province
    @GetMapping("/province")
    public List<String> province(){
        return beachServices.findProvince();
    }


}
