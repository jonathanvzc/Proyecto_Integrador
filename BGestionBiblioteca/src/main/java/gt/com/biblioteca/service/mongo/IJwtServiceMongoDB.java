package gt.com.biblioteca.service.mongo;

import gt.com.biblioteca.model.mongo.UsuarioDocument;
import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface IJwtServiceMongoDB {
    String extractUsuarioCorreoElectronico(String jwtToken);

    <T> T extractClaim(String jwtToken, @NonNull Function<Claims, T> claimsTFunction);

    String generateToken(UsuarioDocument userDetails);

    String generateToken(Map<String, Object> extraClaims, @NonNull UsuarioDocument userDetails);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    UsuarioDocument getUsuario(String jwtToken);
}
