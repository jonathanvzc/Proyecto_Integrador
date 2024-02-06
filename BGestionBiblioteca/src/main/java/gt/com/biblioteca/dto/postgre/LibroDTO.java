package gt.com.biblioteca.dto.postgre;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LibroDTO {
    private Long libroId;
    private String titulo;
    private String autor;
    private String descripcion;
    private boolean estado;
}
