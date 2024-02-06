package gt.com.biblioteca.auth;

import gt.com.biblioteca.util.GeneroEnum;
import gt.com.biblioteca.util.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRegistro {
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
}
