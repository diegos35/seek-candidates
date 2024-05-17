package seekcandidates.candidates.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private static String SECRET_KEY = "S33K_c4ndid4t3";
  private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

  public String create(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuer("seek")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
        .sign(ALGORITHM);
  }

  public boolean isValid(String jwt) {
    try {
      JWT.require(ALGORITHM)
          .build()
          .verify(jwt);
      return true;
    } catch (JWTVerificationException e) {
      return false;
    }
  }

  public String getUsername(String jwt){
    return JWT.require(ALGORITHM)
        .build()
        .verify(jwt)
        .getSubject();
  }
}
