package gt.com.biblioteca.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAuthenticacion {
    private String correoElectronico;
    private String contrasenia;
}
