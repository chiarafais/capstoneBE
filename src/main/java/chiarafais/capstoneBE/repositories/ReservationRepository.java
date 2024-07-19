package chiarafais.capstoneBE.repositories;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.entites.Reservation;
import chiarafais.capstoneBE.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation ,Long> {

    Optional<Reservation> findByUserAndBeachAndDate(User user, Beach beach, LocalDate date);
}
