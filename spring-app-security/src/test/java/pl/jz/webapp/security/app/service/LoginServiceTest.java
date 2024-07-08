package pl.jz.webapp.security.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jz.webapp.security.app.exceptions.BadRequestException;
import pl.jz.webapp.security.app.model.entity.User;
import pl.jz.webapp.security.app.model.payload.LoginRequest;
import pl.jz.webapp.security.app.model.payload.LoginResponse;
import pl.jz.webapp.security.app.repository.UserRepository;
import pl.jz.webapp.security.app.sec.TokenProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private LoginService loginService;

    private User user;
    private LoginRequest loginRequest;
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequest("","");
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        user = new User();
        user.setEmail("test@example.com");
        user.setPassword(null);

        authentication = mock(Authentication.class);
    }

    @Test
    public void testLogin_Success() {
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(loginRequest.getPassword())).thenReturn("encodedPassword");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.createToken(authentication)).thenReturn("token");

        LoginResponse response = loginService.login(loginRequest);

        assertEquals("token", response.getAccessToken());
        verify(userRepository).saveAndFlush(user);
        verify(userRepository).findByEmail(loginRequest.getEmail());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider).createToken(authentication);
    }

    @Test
    public void testLogin_UserNotFound() {
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

        Exception exception = assertThrows(BadRequestException.class, () -> loginService.login(loginRequest));

        assertEquals("Email not registered by administrator yet.", exception.getMessage());
        verify(userRepository).findByEmail(loginRequest.getEmail());
        verifyNoMoreInteractions(userRepository, passwordEncoder, authenticationManager, tokenProvider);
    }

    @Test
    public void testLogin_BadCredentials() {
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(loginRequest.getPassword())).thenReturn("encodedPassword");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new AuthenticationException("Bad credentials") {});

        Exception exception = assertThrows(BadRequestException.class, () -> loginService.login(loginRequest));

        assertEquals("Bad credentials", exception.getMessage());
        verify(userRepository).findByEmail(loginRequest.getEmail());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoMoreInteractions(tokenProvider);
    }
}