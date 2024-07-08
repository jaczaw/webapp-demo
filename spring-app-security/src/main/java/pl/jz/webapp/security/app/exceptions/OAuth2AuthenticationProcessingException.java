package pl.jz.webapp.security.app.exceptions;

public class OAuth2AuthenticationProcessingException extends RuntimeException {
    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
    }
}
