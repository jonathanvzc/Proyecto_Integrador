package gt.com.biblioteca.model.mongo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "libro")
public class LibroDocument {
    @Id
    private String libroId;
    private String titulo;
    private String autor;
    private String descripcion;
    private boolean estado;
}
