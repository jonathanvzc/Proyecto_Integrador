package gt.com.biblioteca.model.mongo;

import gt.com.biblioteca.util.GeneroEnum;
import gt.com.biblioteca.util.RolEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "usuario")
public class UsuarioDocument implements UserDetails {
    @Id
    private String usuarioId;

    private String nombre;

    private String apellido;

    private GeneroEnum genero;

    private LocalDate fechaNacimiento;

    private String dpi;

    private String direccion;

    private String telefono;

    private String correoElectronico;

    private String contrasenia;

    private RolEnum rol;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

    private boolean estado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
