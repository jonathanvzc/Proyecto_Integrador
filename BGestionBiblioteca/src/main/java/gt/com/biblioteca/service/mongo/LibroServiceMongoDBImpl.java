package gt.com.biblioteca.service.mongo;

import gt.com.biblioteca.dto.mongo.LibroDTO;
import gt.com.biblioteca.mapper.mongo.LibroMapperMongoDB;
import gt.com.biblioteca.model.mongo.LibroDocument;
import gt.com.biblioteca.repository.mongo.ILibroRepositoryMongoDB;
import gt.com.biblioteca.service.ILibroServiceGeneric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Service
@RequiredArgsConstructor
public class LibroServiceMongoDBImpl implements ILibroServiceGeneric<LibroDTO, String> {
    private final LibroMapperMongoDB libroMapperMongoDB;
    private final ILibroRepositoryMongoDB iLibroRepositoryMongoDB;

    @Override
    @Transactional(readOnly = true)
    public List<LibroDTO> getAllLibros() {
        List<LibroDocument> libroEntityListExist = iLibroRepositoryMongoDB.findAllByEstadoIsTrue();
        if (libroEntityListExist != null) {
            return libroEntityListExist.stream().map(libroMapperMongoDB::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LibroDTO> getLibroById(String libroId) {
        LibroDocument libroEntityExist = iLibroRepositoryMongoDB.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroEntityExist != null) {
            return Optional.ofNullable(libroMapperMongoDB.convertToDto(libroEntityExist));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public LibroDTO createLibro(LibroDTO libroDTO) {
        LibroDocument libroEntityNew = libroMapperMongoDB.convertToDocument(libroDTO);
        if (libroEntityNew != null) {
            libroEntityNew.setTitulo(libroDTO.getTitulo());
            libroEntityNew.setAutor(libroDTO.getAutor());
            libroEntityNew.setDescripcion(libroDTO.getDescripcion());
            libroEntityNew.setEstado(true);
            iLibroRepositoryMongoDB.save(libroEntityNew);
        } else {
            return null;
        }
        return libroMapperMongoDB.convertToDto(libroEntityNew);
    }

    @Override
    @Transactional
    public LibroDTO updateLibro(LibroDTO libroDTO) {
        LibroDocument libroDocumentExist = iLibroRepositoryMongoDB.findByLibroIdAndEstadoIsTrue(libroDTO.getLibroId());
        if (libroDocumentExist != null) {
            libroDocumentExist.setTitulo(libroDTO.getTitulo());
            libroDocumentExist.setAutor(libroDTO.getAutor());
            libroDocumentExist.setDescripcion(libroDTO.getDescripcion());
            iLibroRepositoryMongoDB.save(libroDocumentExist);
        } else {
            return null;
        }
        return libroMapperMongoDB.convertToDto(libroDocumentExist);
    }

    @Override
    @Transactional
    public LibroDTO deactiveLibroById(String libroId) {
        LibroDocument libroDocumentExist = iLibroRepositoryMongoDB.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroDocumentExist != null) {
            libroDocumentExist.setEstado(false);
            iLibroRepositoryMongoDB.save(libroDocumentExist);
        } else {
            return null;
        }
        return libroMapperMongoDB.convertToDto(libroDocumentExist);
    }

    @Override
    @Transactional
    public LibroDTO deleteLibroById(String libroId) {
        LibroDocument libroDocumentExist = iLibroRepositoryMongoDB.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroDocumentExist != null) {
            iLibroRepositoryMongoDB.delete(libroDocumentExist);
        }
        libroMapperMongoDB.convertToDto(libroDocumentExist);
        return null;
    }
}
