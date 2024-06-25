package pl.jz.webapp.security.app.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull Long id,
        @NotBlank String login,
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String email) {
}
