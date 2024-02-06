package gt.com.biblioteca.service.postgre;

import gt.com.biblioteca.model.postgre.UsuarioEntity;
import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface IJwtServicePostgreSQL {
    String extractUsuarioCorreoElectronico(String jwtToken);

    <T> T extractClaim(String jwtToken, @NonNull Function<Claims, T> claimsTFunction);

    String generateToken(UsuarioEntity userDetails);

    String generateToken(Map<String, Object> extraClaims, @NonNull UsuarioEntity userDetails);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    UsuarioEntity getUsuario(String jwtToken);
}
