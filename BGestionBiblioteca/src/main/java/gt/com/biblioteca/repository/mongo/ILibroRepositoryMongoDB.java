package gt.com.biblioteca.repository.mongo;

import gt.com.biblioteca.model.mongo.LibroDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepositoryMongoDB extends MongoRepository<LibroDocument, String> {
    List<LibroDocument> findAllByEstadoIsTrue();

    LibroDocument findByLibroIdAndEstadoIsTrue(String libroId);
}
