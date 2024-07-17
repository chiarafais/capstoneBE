package chiarafais.capstoneBE.payloads.Beach;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BeachDTO (
        @NotEmpty(message = "Obbligatorio inserire una provincia")
        @Size(min = 3, max= 30, message = "La provincia deve essere compresa tra i 3 e i 30 caratteri")
        String province,
        @NotEmpty(message = "Obbligatorio inserire un comune")
        @Size(min = 3, max= 30, message = "Il comune deve essere compresa tra i 3 e i 30 caratteri")
        String comune,
        @NotEmpty(message = "Obbligatorio inserire il nome della spiaggia")
        @Size(min = 3, max= 30, message = "Il nome della spiaggia deve essere compresa tra i 3 e i 30 caratteri")
        String name_beach,
        @NotNull(message = "Obbligatorio inserire numero massimo di persone ")
        int max_people,
        @NotNull(message = "Obbligatorio inserire il prezzo d'ingresso")
        int price_entry,
        @NotNull(message = "Inserisci prezzo del parcheggio")
        int price_parking,
        boolean close_number,
        boolean establishment,
        @NotEmpty(message = "Obbligatorio inserire il nome della spiaggia")
        @Size(min = 3, max= 150, message = "Il nome della spiaggia deve essere compresa tra i 3 e i 30 caratteri")
        String img_beach
){
}
