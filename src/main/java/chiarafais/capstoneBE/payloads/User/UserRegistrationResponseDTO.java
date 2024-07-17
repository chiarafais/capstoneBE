package chiarafais.capstoneBE.payloads.User;

import jakarta.validation.constraints.NotNull;

public record UserRegistrationResponseDTO(@NotNull long userId) {
}
