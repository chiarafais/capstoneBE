package chiarafais.capstoneBE.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@JsonIgnoreProperties({"beach"})
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

    private int peopleNumber;


    public Reservation(Beach beach, User user, LocalDate dateStart, int peopleNumber) {
        this.beach = beach;
        this.user = user;
        this.dateStart = dateStart;
        this.dateEnd= dateStart.plusDays(1);
        this.peopleNumber= peopleNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", peopleNumber=" + peopleNumber +
                '}';
    }
}
