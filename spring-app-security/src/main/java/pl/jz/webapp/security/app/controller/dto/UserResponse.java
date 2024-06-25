package pl.jz.webapp.security.app.controller.dto;

import pl.jz.webapp.security.app.model.enums.AuthProvider;

public record UserResponse(
        Long id,
        String login,
        String name,
        String surname,
        String email,
        String firstname,
        String lastname,
        AuthProvider authProvider,
        String imageUrl
        )
{
}
