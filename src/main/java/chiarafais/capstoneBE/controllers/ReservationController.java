package chiarafais.capstoneBE.controllers;


import chiarafais.capstoneBE.entites.Reservation;
import chiarafais.capstoneBE.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices;

    // 1. POST http://localhost:3001/reservation  (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestParam Long userId,@RequestParam Long beachId,@RequestParam String date){
        LocalDate reservationDate = LocalDate.parse(date);
        return reservationServices.saveNewReservation(userId,beachId,reservationDate);
    }

//    // 2. GET http://localhost:3001/reservation
//    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public Page<Reservation> findAllReservation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20"){
//        return reservationServices.findAll
//    }

    // 3. GET http://localhost:3001/beach/{id_spiaggia} ritorna la specifica spiaggia

    // 4. PUT http://localhost:3001/beach/{id_spiaggia} + body modifica della specifica spiaggia

    // 5. DELETE http://localhost:3001/beach/{id_spiaggia} elimina la specifica spiaggia

}
