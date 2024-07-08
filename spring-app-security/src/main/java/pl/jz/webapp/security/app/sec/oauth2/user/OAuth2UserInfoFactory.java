package pl.jz.webapp.security.app.sec.oauth2.user;

import pl.jz.webapp.security.app.exceptions.OAuth2AuthenticationProcessingException;
import pl.jz.webapp.security.app.model.enums.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    private OAuth2UserInfoFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.LINKEDIN.toString())) {
            return new LinkedinOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.TWITTER.toString())) {
            return new TwitterOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(String.format("Login with %s is not supported.", registrationId));
        }
    }
    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        return switch (authProvider){
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            case FACEBOOK -> new FacebookOAuth2UserInfo(attributes);
            case GITHUB -> new GithubOAuth2UserInfo(attributes);
            case LINKEDIN -> new LinkedinOAuth2UserInfo(attributes);
            case TWITTER -> new TwitterOAuth2UserInfo(attributes);
            case null -> throw new NullPointerException(
                    "AuthProvider type cannot be null");

        };
    }

}