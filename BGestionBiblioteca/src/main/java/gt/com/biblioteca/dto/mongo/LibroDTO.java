package gt.com.biblioteca.dto.mongo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LibroDTO {
    private String libroId;
    private String titulo;
    private String autor;
    private String descripcion;
    private boolean estado;
}
