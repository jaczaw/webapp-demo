package pl.jz.webapp.security.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jz.webapp.security.app.controller.dto.UserResponse;
import pl.jz.webapp.security.app.exceptions.UserNotFoundException;
import pl.jz.webapp.security.app.mapper.UserMapper;
import pl.jz.webapp.security.app.model.entity.User;
import pl.jz.webapp.security.app.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    /**
     * @return 
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public User validateAndGetUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User id %s not found".formatted(id)));
    }

    /**
     * @param user 
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param user 
     */
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserInfoById(Long id) {
        log.debug("Getting user info by id: {}", id);

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: %s.".formatted(id)));

        return userMapper.toUserResponse(user);
    }
}
