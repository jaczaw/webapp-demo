package pl.jz.webapp.security.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jz.webapp.security.app.exceptions.BadRequestException;
import pl.jz.webapp.security.app.model.entity.User;
import pl.jz.webapp.security.app.model.payload.LoginRequest;
import pl.jz.webapp.security.app.model.payload.LoginResponse;
import pl.jz.webapp.security.app.repository.UserRepository;
import pl.jz.webapp.security.app.sec.TokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginResponse login(LoginRequest loginRequest) {
        log.debug("Login request: {}", loginRequest);
        Authentication authentication;

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("Email not registered by administrator yet."));

        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userRepository.saveAndFlush(user);
        }

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new BadRequestException("Bad credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return new LoginResponse(token);
    }
}
