package pl.jz.webapp.security.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jz.webapp.security.app.controller.dto.CreateUserRequest;
import pl.jz.webapp.security.app.controller.dto.UpdateUserRequest;
import pl.jz.webapp.security.app.controller.dto.UserResponse;
import pl.jz.webapp.security.app.mapper.UserMapper;
import pl.jz.webapp.security.app.model.entity.User;
import pl.jz.webapp.security.app.sec.CurrentUser;
import pl.jz.webapp.security.app.sec.UserPrincipal;
import pl.jz.webapp.security.app.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getUserInfoById(userPrincipal.getId()));
    }

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.getUsers()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        User user = userService.validateAndGetUserById(id);
        return userMapper.toUserResponse(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        User user = userService.saveUser(userMapper.toUser(createUserRequest));
        return userMapper.toUserResponse(user);
    }
    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest){
        User user = userService.validateAndGetUserById(id);
        userMapper.updateUser(updateUserRequest,user);
        user = userService.saveUser(user);
        return userMapper.toUserResponse(user);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userService.validateAndGetUserById(id);
        userService.deleteUser(user);
    }
}
