package pl.jz.webapp.security.app.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.jz.webapp.security.app.config.AppProperties;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final AppProperties appProperties;
    private Algorithm algorithm;

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        algorithm = Algorithm.HMAC256(appProperties.getAuth().getTokenSecret());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return JWT.create()
                .withSubject(Long.toString(userPrincipal.getId()))
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public Long getUserIdFromToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String subject = decodedJWT.getSubject();
        return Long.parseLong(subject);
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid or expired JWT.");
        }
        return false;
    }

}
