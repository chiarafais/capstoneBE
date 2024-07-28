package chiarafais.capstoneBE.payloads.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @NotBlank(message = "Inserire un username.")
        @Size(min = 5, message = "Inserire uno username almeno di 5 caratteri")
        String username,
        @NotBlank(message = "Inserire un nome.")
        @Size(min = 3, max = 50, message = "Inserire un nome compreso tra i 3 e i 50 caratteri")
        String name,
        @NotEmpty(message = "Inserire un cognome.")
        @Size(min = 3, max = 50, message = "Inserire un cognome compreso tra i 3 e i 50 caratteri")
        String surname
) {

}
