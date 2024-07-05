package pl.jz.webapp.shell.command.model.dto;

public record UserResponse(
        Long id,
        String login,
        String name,
        String surname,
        String email,
        String firstname,
        String lastname,
        String imageUrl
        )
{
}
