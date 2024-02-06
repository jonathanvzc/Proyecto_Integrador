package gt.com.biblioteca.dto.postgre;

import gt.com.biblioteca.model.postgre.LibroEntity;
import gt.com.biblioteca.model.postgre.UsuarioEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrestamoDTO {
    private Long prestamoId;
    private LibroEntity libro;
    private UsuarioEntity usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean estado;
}
