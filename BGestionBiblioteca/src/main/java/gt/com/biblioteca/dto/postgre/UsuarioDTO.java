package gt.com.biblioteca.dto.postgre;

import gt.com.biblioteca.util.GeneroEnum;
import gt.com.biblioteca.util.RolEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsuarioDTO {
    private Long usuarioId;
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
