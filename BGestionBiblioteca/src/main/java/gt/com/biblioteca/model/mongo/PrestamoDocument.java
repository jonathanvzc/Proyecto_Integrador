package gt.com.biblioteca.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "prestamo")
public class PrestamoDocument {
    @Id
    private String prestamoId;
    private LibroDocument libro;
    private UsuarioDocument usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean estado;
}
