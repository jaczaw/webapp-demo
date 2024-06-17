package pl.jz.webapp.shell.command.model.dto;

public record ToDoResponse(Long id,String description, boolean completed) {
}