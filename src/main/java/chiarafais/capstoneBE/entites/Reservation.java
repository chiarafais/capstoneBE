package chiarafais.capstoneBE.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private LocalDate dateStart;

    private LocalDate dateEnd;

    public Reservation(Beach beach, User user, LocalDate dateStart) {
        this.beach = beach;
        this.user = user;
        this.dateStart = dateStart;
        this.dateEnd= dateStart.plusDays(1);
    }
}
