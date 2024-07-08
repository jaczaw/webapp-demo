package pl.jz.webapp.security.app.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.http.HttpStatus;
import pl.jz.webapp.security.app.config.CustomDateSerializer;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {

    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime time = LocalDateTime.now();

    private String message;

    private HttpStatus httpStatus;

    public ErrorMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}