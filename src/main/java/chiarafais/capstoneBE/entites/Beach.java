package chiarafais.capstoneBE.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "spiagge")
@NoArgsConstructor
@Data
public class Beach {
    @Id
    @GeneratedValue  //  (strategy = GenerationType.IDENTITY)
    @Column(name = "id_spiaggia", nullable = false)
    private Long id;

    @Column(name = "provincia", nullable = false)
    private String province;

    @Column(name = "comune", nullable = false)
    private String comune;

    @Column(name = "nome_spiaggia", nullable = false)
    private String name_beach;

    @Column(name = "max_persone", nullable = false)
    private int max_people;

    @Column(name = "prezzo_ingresso", nullable = false)
    private int price_entry;

    @Column(name = "costo_parcheggio", nullable = false)
    private int price_parking;

    @Column(name = "numero_chiuso", nullable = false)
    private boolean close_number;

    @Column(name = "stabilimento", nullable = false)
    private boolean establishment;

    @Column(name = "img_spiaggia", nullable = false)
    private String img_beach;

    @Column(name = "posti_occupati", nullable = false)
    private int reserved_places;

    @OneToMany(mappedBy = "beach")
    @JsonIgnore
    private List<Reservation> reservations;


    public Beach(String province, String comune, String name_beach, int max_people, int price_entry, int price_parking, boolean close_number, boolean establishment, String img_beach) {
        this.province = province;
        this.comune = comune;
        this.name_beach = name_beach;
        this.max_people = max_people;
        this.price_entry = price_entry;
        this.price_parking = price_parking;
        this.close_number = close_number;
        this.establishment = establishment;
        this.img_beach = img_beach;
        this.reserved_places = 0;
    }

}
