package gt.com.biblioteca.repository.mongo;

import gt.com.biblioteca.model.mongo.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositoryMongoDB extends MongoRepository<UsuarioDocument, String> {
    Optional<UsuarioDocument> findByCorreoElectronicoAndEstadoIsTrue(String correoElectronico);
}
