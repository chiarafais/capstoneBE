package chiarafais.capstoneBE.payloads.User;

import jakarta.validation.constraints.*;

public record UserRequiredDTO(
        @NotBlank(message = "Inserire un username.")
        @Size(min = 5, message = "Inserire uno username almeno di 5 caratteri")
        String username,
        @NotBlank(message = "Inserire un nome.")
        @Size(min = 3, max = 50, message = "Inserire un nome compreso tra i 3 e i 50 caratteri")
        String name,
        @NotEmpty(message = "Inserire un cognome.")
        @Size(min = 3, max = 50, message = "Inserire un cognome compreso tra i 3 e i 50 caratteri")
        String surname,
        @Email(message = "Email non valida!")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email non valida!")
        String email,
        @NotBlank(message = "Inserire una password.")
        @Size(min = 8, message = "Inserire una password di almeno 8 caratteri")
        String password
) {
}
