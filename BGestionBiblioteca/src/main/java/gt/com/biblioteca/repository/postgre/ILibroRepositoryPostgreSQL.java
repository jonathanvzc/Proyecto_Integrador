package gt.com.biblioteca.repository.postgre;

import gt.com.biblioteca.model.postgre.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepositoryPostgreSQL extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findAllByEstadoIsTrue();

    LibroEntity findByLibroIdAndEstadoIsTrue(Long libroId);
}
