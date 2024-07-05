package pl.jz.webapp.shell.command.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pl.jz.webapp.shell.command.client.SecurityApiClient;
import pl.jz.webapp.shell.command.model.dto.CreateUserRequest;
import pl.jz.webapp.shell.command.model.dto.UpdateUserRequest;
import pl.jz.webapp.shell.command.model.dto.UserResponse;

import java.util.List;
import java.util.Objects;

@Slf4j
@ShellComponent
@AllArgsConstructor
public class UserShellCommands {

    private final SecurityApiClient securityApiClient;
    private final ObjectMapper objectMapper;
    private static final String FORMAT = "%s :: %s";

    @ShellMethod(key="hello-world")
    public String helloWorld(
            @ShellOption(defaultValue = "menage Users") String arg
    ) {
        return "Hello world " + arg;
    }

    @ShellMethod(key="get-user")
    public String getUser(String id) {
        ResponseEntity<UserResponse> responseEntity = securityApiClient.getUser(id);
        return FORMAT.formatted(responseEntity.getStatusCode(), toJson(responseEntity.getBody()));
    }

    @ShellMethod(key="get-users")
    public String getUsers() {
        ResponseEntity<List<UserResponse>> responseEntity = securityApiClient.getUsers();
        List<String> list = Objects.requireNonNull(responseEntity.getBody()).stream().map(this::toJson).toList();
        return FORMAT.formatted(responseEntity.getStatusCode(), list.toString());
    }

    @ShellMethod(key="add-user")
    public String addUser(String login, String name, String surname, String email) {
        ResponseEntity<UserResponse> responseEntity = securityApiClient
                .createUser(new CreateUserRequest(login,name, surname,email));

        return FORMAT.formatted(responseEntity.getStatusCode(),toJson(responseEntity.getBody()));
    }

    @ShellMethod(key="update-user")
    public String updateUser(String id,
                             @ShellOption(defaultValue = ShellOption.NULL) String login,
                             @ShellOption(defaultValue = ShellOption.NULL) String name,
                             @ShellOption(defaultValue = ShellOption.NULL) String surname,
                             @ShellOption(defaultValue = ShellOption.NULL) String email) {

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(login, name, surname, email);
        ResponseEntity<UserResponse> responseEntity = securityApiClient
                .updateUser(id,updateUserRequest);

        return FORMAT.formatted(responseEntity.getStatusCode(),toJson(responseEntity.getBody()));
    }

    @ShellMethod(key="delete-user")
    public String deleteUser(String id) {
        ResponseEntity<UserResponse> responseEntity = securityApiClient
                .deleteUser(id);
        UserResponse deleteUser = responseEntity.getBody();
        String result = deleteUser==null ? "ERROR" : deleteUser.email();

        return FORMAT.formatted(responseEntity.getStatusCode(),result);
    }

    private String toJson(UserResponse userResponse) {
        try {
            return objectMapper.writeValueAsString(userResponse);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
