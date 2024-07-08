package pl.jz.webapp.security.app.service;

import pl.jz.webapp.security.app.controller.dto.UserResponse;
import pl.jz.webapp.security.app.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User validateAndGetUserById(Long id);

    User saveUser(User user);

    void deleteUser(User user);

    UserResponse getUserInfoById(Long id);
}
