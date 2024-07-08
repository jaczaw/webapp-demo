package pl.jz.webapp.shell.command.model.dto;

public record UpdateUserRequest(String login, String name, String surname, String email) {
}
