package pl.jz.webapp.shell.command.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import pl.jz.webapp.shell.command.client.ToDoApiClient;
import pl.jz.webapp.shell.command.model.dto.ToDoResponse;

import java.util.List;
import java.util.Objects;

@ShellComponent
@AllArgsConstructor
public class ToDoShellCommands {

    private final ToDoApiClient toDoApiClient;
    private final ObjectMapper objectMapper;
    private static final String FORMAT = "%s :: %s";

    @ShellMethod("Get TopDo by id")
    public String getTodo(String id) {
        ResponseEntity<ToDoResponse> responseEntity = toDoApiClient.getToDo(id);
        return FORMAT.formatted(responseEntity.getStatusCode(), toJson(responseEntity.getBody()));
    }

    @ShellMethod("Get all ToDo")
    public String getToDoAll() {
        ResponseEntity<List<ToDoResponse>> responseEntity = toDoApiClient.getToDo();
        List<String> list = Objects.requireNonNull(responseEntity.getBody()).stream().map(this::toJson).toList();
        return FORMAT.formatted(responseEntity.getStatusCode(), list.toString());
    }


    private String toJson(ToDoResponse toDoResponse) {
        try {
            return objectMapper.writeValueAsString(toDoResponse);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
