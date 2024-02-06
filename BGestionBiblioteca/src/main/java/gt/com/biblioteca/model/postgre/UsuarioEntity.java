package gt.com.biblioteca.model.postgre;

import gt.com.biblioteca.util.GeneroEnum;
import gt.com.biblioteca.util.RolEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotEmpty
    @Size(min = 13, max = 13, message = "13 caracteres como minimo y 13 caracteres como maximo")
    @Column(name = "dpi", nullable = false)
    private String dpi;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotEmpty
    @Size(min = 12, max = 12, message = "12 caracteres como minimo y 12 caracteres como maximo")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @NotEmpty
    @Size(min = 30, max = 255, message = "75 caracteres como minimo y 255 caracteres como maximo")
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
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
