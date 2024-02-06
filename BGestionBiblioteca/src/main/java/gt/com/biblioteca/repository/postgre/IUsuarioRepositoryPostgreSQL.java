package gt.com.biblioteca.repository.postgre;

import gt.com.biblioteca.model.postgre.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositoryPostgreSQL extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreoElectronicoAndEstadoIsTrue(String correoElectronico);
}
