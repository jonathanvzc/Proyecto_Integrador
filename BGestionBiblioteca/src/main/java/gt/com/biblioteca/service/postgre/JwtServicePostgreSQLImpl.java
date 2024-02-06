package gt.com.biblioteca.service.postgre;

import gt.com.biblioteca.model.postgre.UsuarioEntity;
import gt.com.biblioteca.repository.postgre.IUsuarioRepositoryPostgreSQL;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServicePostgreSQLImpl implements IJwtServicePostgreSQL {
    private static final String SECRET_KEY = "FAK7upq2PNV+OHKhBv2KghqMfYruBzxk2ZJQo5xegJs=";
    private final IUsuarioRepositoryPostgreSQL iUsuarioRepositoryPostgreSQL;

    @Override
    public String extractUsuarioCorreoElectronico(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String jwtToken, @NonNull Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsTFunction.apply(claims);
    }

    @Override
    public String generateToken(UsuarioEntity userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, @NonNull UsuarioEntity userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .claim("rol", userDetails.getRol().name())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String usuarioCorreoElectronico = extractUsuarioCorreoElectronico(jwtToken);
        return (usuarioCorreoElectronico.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    @Override
    public UsuarioEntity getUsuario(String jwtToken) {
        Claims claims = extractAllClaims(jwtToken);
        String usuarioCorreoElectronico = claims.getSubject();
        return iUsuarioRepositoryPostgreSQL.findByCorreoElectronicoAndEstadoIsTrue(usuarioCorreoElectronico).orElseThrow();
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private @NonNull SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
