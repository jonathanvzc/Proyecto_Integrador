package gt.com.biblioteca.model.postgre;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "prestamo")
public class PrestamoDocument {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "prestamo_id", nullable = false)
    private Long prestamoId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private LibroEntity libro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @NotNull
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @NotNull
    @Column(name = "fecha_devolucion", nullable = false)
    private LocalDate fechaDevolucion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;
}
