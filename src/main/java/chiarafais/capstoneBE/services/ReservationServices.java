package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.entites.Reservation;
import chiarafais.capstoneBE.entites.User;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.exceptions.NotFoundException;
import chiarafais.capstoneBE.repositories.BeachRepository;
import chiarafais.capstoneBE.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServices {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private BeachServices beachServices;

    @Autowired
    private BeachRepository beachRepository;

    public Reservation saveNewReservation(long userId, long beachId, LocalDate date, int peopleNumber){

        User user = userServices.findById(userId);
        Beach beach = beachServices.findById(beachId);

        if (beach.getReserved_places() >= beach.getMax_people()) {
            throw new BadRequestException("Spiaggia piena, non è possibile prenotare, riprova più tardi!");
        }

         Optional<Reservation> reservationExist = reservationRepository.findByUserAndBeachAndDateStart(user, beach, date);
        if (reservationExist.isPresent()){
            throw new BadRequestException("Hai già una prenotazione per questa data");
        }

        Reservation reservation = new Reservation(beach,user,date,peopleNumber);
        reservationRepository.save(reservation);

        List<Reservation> reservations_list = this.reservationRepository.number_reservation(beach.getId());
        System.out.println(reservations_list);
        Long total_people = reservations_list.stream().mapToLong(Reservation::getPeopleNumber).sum();

//        beach.setReserved_places(beach.getReserved_places() + peopleNum);
        beach.setReserved_places(total_people.intValue());

        beachServices.save(beach);

        return reservation;

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

    public void refreshPeopleNum(){
       List<Beach> prova = this.beachRepository.findAll();

        for (Beach element:prova) {
        int peopleNum = this.reservationRepository.number_reservation(element.getId()).stream().mapToInt(Reservation::getPeopleNumber).sum();
        element.setReserved_places(peopleNum);
        beachRepository.save(element);
        }

    }

}
