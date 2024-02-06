package gt.com.biblioteca.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespuestaAuthenticacion {
    private String jwtToken;
}
