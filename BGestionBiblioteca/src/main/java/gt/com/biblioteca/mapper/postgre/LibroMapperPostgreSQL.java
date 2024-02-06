package gt.com.biblioteca.mapper.postgre;

import gt.com.biblioteca.dto.postgre.LibroDTO;
import gt.com.biblioteca.model.postgre.LibroEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LibroMapperPostgreSQL {
    private final ModelMapper modelMapper;

    public LibroDTO convertToDto(LibroEntity libroEntity) {
        return modelMapper.map(libroEntity, LibroDTO.class);
    }

    public LibroEntity convertToEntity(LibroDTO libroDTO) {
        return modelMapper.map(libroDTO, LibroEntity.class);
    }
}
