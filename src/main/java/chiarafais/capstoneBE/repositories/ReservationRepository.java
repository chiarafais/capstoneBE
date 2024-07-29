package chiarafais.capstoneBE.repositories;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.entites.Reservation;
import chiarafais.capstoneBE.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation ,Long> {

    Optional<Reservation> findByUserAndBeachAndDateStart(User user, Beach beach, LocalDate dateStart);

    @Query("SELECT r FROM Reservation r WHERE r.dateStart = CURRENT_DATE AND r.beach.id = :beachId")
    List<Reservation> number_reservation(@Param("beachId")Long beachId);

    @Query("SELECT p FROM Reservation p WHERE p.user.id = :userId AND p.dateStart >= CURRENT_DATE")
    List<Reservation> reservations_user(@Param("userId")Long userId);
}
