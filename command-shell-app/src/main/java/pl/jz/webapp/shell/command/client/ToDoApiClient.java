package pl.jz.webapp.shell.command.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import pl.jz.webapp.shell.command.model.dto.CreateRequest;
import pl.jz.webapp.shell.command.model.dto.ToDoResponse;
import pl.jz.webapp.shell.command.model.dto.UpdateRequest;

import java.util.List;

@HttpExchange("/api/todos")
public interface ToDoApiClient {

    @GetExchange("/{id}")
    ResponseEntity<ToDoResponse> getToDo(@PathVariable String id);

    @GetExchange
    ResponseEntity<List<ToDoResponse>> getToDo();

    @PostExchange
    ResponseEntity<ToDoResponse> createToDo(@RequestBody CreateRequest createRequest);

    @PatchExchange("/{id}")
    ResponseEntity<ToDoResponse> updateToDo(@PathVariable String id, @RequestBody UpdateRequest updateRequest);

    @DeleteExchange("/{id}")
    ResponseEntity<Void> deleteMovie(@PathVariable String id);
}
