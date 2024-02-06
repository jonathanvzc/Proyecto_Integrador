package gt.com.biblioteca.repository.postgre;

import gt.com.biblioteca.model.postgre.PrestamoDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrestamoRepositoryPostgreSQL extends JpaRepository<PrestamoDocument, Long> {
    List<PrestamoDocument> findAllByEstadoIsTrue();

    PrestamoDocument findByPrestamoIdAndEstadoIsTrue(Long prestamoId);
}
