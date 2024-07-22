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

    // 1. POST http://localhost:3001/reservation?userId=&beachId=&date=
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestParam Long userId,@RequestParam Long beachId,@RequestParam String date){
        LocalDate reservationDate = LocalDate.parse(date);
        return reservationServices.saveNewReservation(userId,beachId,reservationDate);
    }

//    // 2. GET http://localhost:3001/reservation
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Reservation> findAllReservation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return reservationServices.findAll(page,size);
    }

    // 3. GET http://localhost:3001/reservation/ ritorna la specifica reservation
    @GetMapping("/{id_prenotazione}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Reservation findById(@PathVariable long id_prenotazione){return reservationServices.findById(id_prenotazione);}


    // 4. DELETE http://localhost:3001/reservation/{id_spiaggia} elimina la specifica reservation
    @DeleteMapping("/{id_prenotazione}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id_prenotazione){
        reservationServices.deleteReservation(id_prenotazione);
    }
}
