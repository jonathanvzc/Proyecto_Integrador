package gt.com.biblioteca.dto.mongo;

import gt.com.biblioteca.model.mongo.LibroDocument;
import gt.com.biblioteca.model.mongo.UsuarioDocument;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrestamoDTO {
    private String prestamoId;
    private LibroDocument libro;
    private UsuarioDocument usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean estado;
}
