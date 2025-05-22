package peaksoft.java15.demo111.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import peaksoft.java15.demo111.entity.User;

import java.time.ZonedDateTime;

@Component
public class JwtTokenUtil {

    @Value("${app.security.jwt.secret_key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration}")
    private Long expiration;

    public String generateToken(User user){
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expirationTime = now.plusSeconds(expiration);

        return JWT.create()
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(now.toInstant())
                .withExpiresAt(expirationTime.toInstant())
                .sign(getAlgorithm());
    }
    public Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secretKey);
    }

}