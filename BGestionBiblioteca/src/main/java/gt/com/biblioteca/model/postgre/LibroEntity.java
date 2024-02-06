package gt.com.biblioteca.model.postgre;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "libro")
public class LibroEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "libro_id", nullable = false)
    private Long libroId;

    @NotEmpty
    @Size(min = 5, max = 100, message = "5 caracteres como minimo y 100 caracteres como maximo")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotEmpty
    @Size(min = 5, max = 100, message = "5 caracteres como minimo y 100 caracteres como maximo")
    @Column(name = "autor", nullable = false)
    private String autor;

    @NotEmpty
    @Size(min = 5, max = 255, message = "5 caracteres como minimo y 255 caracteres como maximo")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;
}
