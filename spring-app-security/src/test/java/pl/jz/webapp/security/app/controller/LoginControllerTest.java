package pl.jz.webapp.security.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.jz.webapp.security.app.config.SecurityConfig;
import pl.jz.webapp.security.app.model.payload.LoginRequest;
import pl.jz.webapp.security.app.model.payload.LoginResponse;
import pl.jz.webapp.security.app.sec.TokenAuthenticationFilter;
import pl.jz.webapp.security.app.service.LoginService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginRequest loginRequest;
    private LoginResponse loginResponse;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequest("","");
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        loginResponse = new LoginResponse("");
        loginResponse.setAccessToken("token");
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(loginService.login(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/auth/login1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loginResponse)));
    }

    @Test
    public void testLogin_BadRequest() throws Exception {
        loginRequest.setEmail(""); // Invalid email

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAuthentication() throws Exception {
        this.mockMvc.
                perform(post("/login").
                        content("{\"email\":\"jaca@po.pl\",\"password\":\"admin\"}").
                        header(HttpHeaders.CONTENT_TYPE,
                                "application/json")).
                andDo(print()).andExpect(status().isOk());
    }
}