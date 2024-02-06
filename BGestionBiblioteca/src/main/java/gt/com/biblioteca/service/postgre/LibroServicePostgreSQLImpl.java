package gt.com.biblioteca.service.postgre;

import gt.com.biblioteca.dto.postgre.LibroDTO;
import gt.com.biblioteca.model.postgre.LibroEntity;
import gt.com.biblioteca.mapper.postgre.LibroMapperPostgreSQL;
import gt.com.biblioteca.repository.postgre.ILibroRepositoryPostgreSQL;
import gt.com.biblioteca.service.ILibroServiceGeneric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

//@Service
@RequiredArgsConstructor
public class LibroServicePostgreSQLImpl implements ILibroServiceGeneric<LibroDTO, Long> {
    private final LibroMapperPostgreSQL libroMapperPostgreSQL;
    private final ILibroRepositoryPostgreSQL iLibroRepositoryPostgreSQL;

    @Override
    @Transactional(readOnly = true)
    public List<LibroDTO> getAllLibros() {
        List<LibroEntity> libroEntityListExist = iLibroRepositoryPostgreSQL.findAllByEstadoIsTrue();
        if (libroEntityListExist != null) {
            return libroEntityListExist.stream().map(libroMapperPostgreSQL::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LibroDTO> getLibroById(Long libroId) {
        LibroEntity libroEntityExist = iLibroRepositoryPostgreSQL.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroEntityExist != null) {
            return Optional.ofNullable(libroMapperPostgreSQL.convertToDto(libroEntityExist));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public LibroDTO createLibro(LibroDTO libroDTO) {
        LibroEntity libroEntityNew = libroMapperPostgreSQL.convertToEntity(libroDTO);
        if (libroEntityNew != null) {
            libroEntityNew.setTitulo(libroDTO.getTitulo());
            libroEntityNew.setAutor(libroDTO.getAutor());
            libroEntityNew.setDescripcion(libroDTO.getDescripcion());
            libroEntityNew.setEstado(true);
            iLibroRepositoryPostgreSQL.save(libroEntityNew);
        } else {
            return null;
        }
        return libroMapperPostgreSQL.convertToDto(libroEntityNew);
    }

    @Override
    @Transactional
    public LibroDTO updateLibro(LibroDTO libroDTO) {
        LibroEntity libroEntityExist = iLibroRepositoryPostgreSQL.findByLibroIdAndEstadoIsTrue(libroDTO.getLibroId());
        if (libroEntityExist != null) {
            libroEntityExist.setTitulo(libroDTO.getTitulo());
            libroEntityExist.setAutor(libroDTO.getAutor());
            libroEntityExist.setDescripcion(libroDTO.getDescripcion());
            iLibroRepositoryPostgreSQL.save(libroEntityExist);
        } else {
            return null;
        }
        return libroMapperPostgreSQL.convertToDto(libroEntityExist);
    }

    @Override
    @Transactional
    public LibroDTO deactiveLibroById(Long libroId) {
        LibroEntity libroEntityExit = iLibroRepositoryPostgreSQL.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroEntityExit != null) {
            libroEntityExit.setEstado(false);
            iLibroRepositoryPostgreSQL.save(libroEntityExit);
        } else {
            return null;
        }
        return libroMapperPostgreSQL.convertToDto(libroEntityExit);
    }

    @Override
    @Transactional
    public LibroDTO deleteLibroById(Long libroId) {
        LibroEntity libroEntityExist = iLibroRepositoryPostgreSQL.findByLibroIdAndEstadoIsTrue(libroId);
        if (libroEntityExist != null) {
            iLibroRepositoryPostgreSQL.delete(libroEntityExist);
        }
        libroMapperPostgreSQL.convertToDto(libroEntityExist);
        return null;
    }
}
