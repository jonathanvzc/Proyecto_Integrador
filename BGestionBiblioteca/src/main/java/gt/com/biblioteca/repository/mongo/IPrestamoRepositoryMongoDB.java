package gt.com.biblioteca.repository.mongo;

import gt.com.biblioteca.model.mongo.PrestamoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrestamoRepositoryMongoDB extends MongoRepository<PrestamoDocument, String> {
    List<PrestamoDocument> findAllByEstadoIsTrue();

    PrestamoDocument findByPrestamoIdAndEstadoIsTrue(String prestamoId);
}
