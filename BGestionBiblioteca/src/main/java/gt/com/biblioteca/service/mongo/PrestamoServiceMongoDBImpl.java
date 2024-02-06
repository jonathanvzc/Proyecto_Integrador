package gt.com.biblioteca.service.mongo;

import gt.com.biblioteca.dto.mongo.PrestamoDTO;
import gt.com.biblioteca.mapper.mongo.PrestamoMapperMongoDB;
import gt.com.biblioteca.model.mongo.PrestamoDocument;
import gt.com.biblioteca.repository.mongo.IPrestamoRepositoryMongoDB;
import gt.com.biblioteca.service.IPrestamoServiceGeneric;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
@RequiredArgsConstructor
public class PrestamoServiceMongoDBImpl implements IPrestamoServiceGeneric<PrestamoDTO, String> {
    private final HttpServletRequest httpServletRequest;
    private final IJwtServiceMongoDB iJwtServiceMongoDB;
    private final PrestamoMapperMongoDB prestamoMapperMongoDB;
    private final IPrestamoRepositoryMongoDB iPrestamoRepositoryMongoDB;

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoDTO> getAllPrestamos() {
        List<gt.com.biblioteca.model.mongo.PrestamoDocument> prestamoDocumentListExist = iPrestamoRepositoryMongoDB.findAllByEstadoIsTrue();
        if (prestamoDocumentListExist != null) {
            return prestamoDocumentListExist.stream().map(prestamoMapperMongoDB::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoDTO getPrestamoById(String prestamoId) {
        gt.com.biblioteca.model.mongo.PrestamoDocument prestamoDocumentExist = iPrestamoRepositoryMongoDB.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoDocumentExist != null) {
            return prestamoMapperMongoDB.convertToDto(prestamoDocumentExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public PrestamoDTO createPrestamo(PrestamoDTO prestamoDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtServiceMongoDB.getUsuario(jwtToken);
        gt.com.biblioteca.model.mongo.PrestamoDocument prestamoDocumentNew = prestamoMapperMongoDB.convertToDocument(prestamoDTO);
        if (prestamoDocumentNew != null) {
            prestamoDocumentNew.setUsuario(usuarioLogueado);
            prestamoDocumentNew.setLibro(prestamoDTO.getLibro());
            prestamoDocumentNew.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
            prestamoDocumentNew.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
            prestamoDocumentNew.setEstado(true);
            iPrestamoRepositoryMongoDB.save(prestamoDocumentNew);
        } else {
            return null;
        }
        return prestamoMapperMongoDB.convertToDto(prestamoDocumentNew);
    }

    @Override
    @Transactional
    public PrestamoDTO updatePrestamo(PrestamoDTO prestamoDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtServiceMongoDB.getUsuario(jwtToken);
        PrestamoDocument prestamoDocumentExist = iPrestamoRepositoryMongoDB.findByPrestamoIdAndEstadoIsTrue(prestamoDTO.getPrestamoId());
        if (prestamoDocumentExist != null) {
            prestamoDocumentExist.setUsuario(usuarioLogueado);
            prestamoDocumentExist.setLibro(prestamoDTO.getLibro());
            prestamoDocumentExist.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
            prestamoDocumentExist.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
            iPrestamoRepositoryMongoDB.save(prestamoDocumentExist);
        } else {
            return null;
        }
        return prestamoMapperMongoDB.convertToDto(prestamoDocumentExist);
    }

    @Override
    @Transactional
    public PrestamoDTO deactivePrestamoById(String prestamoId) {
        PrestamoDocument prestamoDocumentExist = iPrestamoRepositoryMongoDB.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoDocumentExist != null) {
            prestamoDocumentExist.setEstado(false);
            iPrestamoRepositoryMongoDB.save(prestamoDocumentExist);
        } else {
            return null;
        }
        return prestamoMapperMongoDB.convertToDto(prestamoDocumentExist);
    }

    @Override
    @Transactional
    public PrestamoDTO deletePrestamoById(String prestamoId) {
        PrestamoDocument prestamoDocumentExist = iPrestamoRepositoryMongoDB.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoDocumentExist != null) {
            iPrestamoRepositoryMongoDB.delete(prestamoDocumentExist);
        } else {
            return null;
        }
        return prestamoMapperMongoDB.convertToDto(prestamoDocumentExist);
    }
}
