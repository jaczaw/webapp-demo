package pl.jz.webapp.shell.command.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

        @NotBlank String login,
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String email) {
}