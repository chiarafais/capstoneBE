package chiarafais.capstoneBE.entites;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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


    private LocalDate date;

    public Reservation(Beach beach, User user, LocalDate date) {
        this.beach = beach;
        this.user = user;
        this.date = date;
    }
}
