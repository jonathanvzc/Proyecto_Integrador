package gt.com.biblioteca.service.postgre;

import gt.com.biblioteca.dto.postgre.PrestamoDTO;
import gt.com.biblioteca.model.postgre.PrestamoDocument;
import gt.com.biblioteca.mapper.postgre.PrestamnoMapperPostgreSQL;
import gt.com.biblioteca.repository.postgre.IPrestamoRepositoryPostgreSQL;
import gt.com.biblioteca.service.IPrestamoServiceGeneric;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

//@Service
@RequiredArgsConstructor
public class PrestamoServicePostgreSQLImpl implements IPrestamoServiceGeneric<PrestamoDTO, Long> {
    private final HttpServletRequest httpServletRequest;
    private final IJwtServicePostgreSQL iJwtServicePostgreSQL;
    private final PrestamnoMapperPostgreSQL prestamnoMapperPostgreSQL;
    private final IPrestamoRepositoryPostgreSQL iPrestamoRepositoryPostgreSQL;

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoDTO> getAllPrestamos() {
        List<PrestamoDocument> prestamoEntityListExist = iPrestamoRepositoryPostgreSQL.findAllByEstadoIsTrue();
        if (prestamoEntityListExist != null) {
            return prestamoEntityListExist.stream().map(prestamnoMapperPostgreSQL::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoDTO getPrestamoById(Long prestamoId) {
        PrestamoDocument prestamoEntityExist = iPrestamoRepositoryPostgreSQL.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoEntityExist != null) {
            return prestamnoMapperPostgreSQL.convertToDto(prestamoEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public PrestamoDTO createPrestamo(PrestamoDTO prestamoDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtServicePostgreSQL.getUsuario(jwtToken);
        PrestamoDocument prestamoEntityNew = prestamnoMapperPostgreSQL.convertToEntity(prestamoDTO);
        if (prestamoEntityNew != null) {
            prestamoEntityNew.setUsuario(usuarioLogueado);
            prestamoEntityNew.setLibro(prestamoDTO.getLibro());
            prestamoEntityNew.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
            prestamoEntityNew.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
            prestamoEntityNew.setEstado(true);
            iPrestamoRepositoryPostgreSQL.save(prestamoEntityNew);
        } else {
            return null;
        }
        return prestamnoMapperPostgreSQL.convertToDto(prestamoEntityNew);
    }

    @Override
    @Transactional
    public PrestamoDTO updatePrestamo(PrestamoDTO prestamoDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtServicePostgreSQL.getUsuario(jwtToken);
        PrestamoDocument prestamoEntityExist = iPrestamoRepositoryPostgreSQL.findByPrestamoIdAndEstadoIsTrue(prestamoDTO.getPrestamoId());
        if (prestamoEntityExist != null) {
            prestamoEntityExist.setUsuario(usuarioLogueado);
            prestamoEntityExist.setLibro(prestamoDTO.getLibro());
            prestamoEntityExist.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
            prestamoEntityExist.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
            iPrestamoRepositoryPostgreSQL.save(prestamoEntityExist);
        } else {
            return null;
        }
        return prestamnoMapperPostgreSQL.convertToDto(prestamoEntityExist);
    }

    @Override
    @Transactional
    public PrestamoDTO deactivePrestamoById(Long prestamoId) {
        PrestamoDocument prestamoEntityExist = iPrestamoRepositoryPostgreSQL.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoEntityExist != null) {
            prestamoEntityExist.setEstado(false);
            iPrestamoRepositoryPostgreSQL.save(prestamoEntityExist);
        } else {
            return null;
        }
        return prestamnoMapperPostgreSQL.convertToDto(prestamoEntityExist);
    }

    @Override
    @Transactional
    public PrestamoDTO deletePrestamoById(Long prestamoId) {
        PrestamoDocument prestamoEntityExist = iPrestamoRepositoryPostgreSQL.findByPrestamoIdAndEstadoIsTrue(prestamoId);
        if (prestamoEntityExist != null) {
            iPrestamoRepositoryPostgreSQL.delete(prestamoEntityExist);
        } else {
            return null;
        }
        return prestamnoMapperPostgreSQL.convertToDto(prestamoEntityExist);
    }
}
