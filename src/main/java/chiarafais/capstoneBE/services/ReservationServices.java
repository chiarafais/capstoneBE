package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.entites.Reservation;
import chiarafais.capstoneBE.entites.User;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.exceptions.NotFoundException;
import chiarafais.capstoneBE.payloads.Reservation.ReservationDTO;
import chiarafais.capstoneBE.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationServices {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private BeachServices beachServices;

    public Reservation saveNewReservation(long userId, long beachId, LocalDate date){
        User user = userServices.findById(userId);
        Beach beach = beachServices.findById(beachId);

        if (beach.getReserved_places() >= beach.getMax_people()) {
            throw new BadRequestException("Spiaggia piena, non è possibile prenotare, riprova più tardi!");
        }

         Optional<Reservation> reservationExist = reservationRepository.findByUserAndBeachAndDate(user, beach, date);
        if (reservationExist.isPresent()){
            throw new BadRequestException("Hai già una prenotazione per questa data");
        }

        beach.setReserved_places(beach.getReserved_places() + 1);
        beachServices.save(beach);

        Reservation reservation = new Reservation(beach,user,date);
        return reservationRepository.save(reservation);
    }
    public Page<Reservation> findAll(int pageNumber, int pageSize){
        Pageable reservations = PageRequest.of(pageNumber, pageSize);
        return reservationRepository.findAll(reservations);
    }
    public Reservation findById(long id_prenotazione){
        return reservationRepository.findById(id_prenotazione).orElseThrow(()-> new NotFoundException("nessuna spiaggia con questo id"));
    }

    public void deleteReservation(long idPrenotazione) {
        Reservation foundReservation = findById(idPrenotazione);
        reservationRepository.delete(foundReservation);
    }

//    public Reservation updateReservation(long idPrenotazione, ReservationDTO body) {
//        Reservation foundReservation = findById(idPrenotazione);
//        return reservationRepository.save(foundReservation);
//    }


}
