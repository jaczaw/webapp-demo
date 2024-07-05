package pl.jz.webapp.shell.command.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import pl.jz.webapp.shell.command.model.dto.*;

import java.util.List;

@HttpExchange("/api/users")
public interface SecurityApiClient {

    @GetExchange("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable String id);

    @GetExchange
    ResponseEntity<List<UserResponse>> getUsers();

    @PostExchange
    ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest);

    @PutExchange("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest updateUserRequest);

    @DeleteExchange("/{id}")
    ResponseEntity<UserResponse> deleteUser(@PathVariable String id);
}
