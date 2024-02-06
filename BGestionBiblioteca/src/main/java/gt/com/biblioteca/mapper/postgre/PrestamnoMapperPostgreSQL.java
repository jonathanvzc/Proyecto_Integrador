package gt.com.biblioteca.mapper.postgre;

import gt.com.biblioteca.dto.postgre.PrestamoDTO;
import gt.com.biblioteca.model.postgre.PrestamoDocument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrestamnoMapperPostgreSQL {
    private final ModelMapper modelMapper;

    public PrestamoDTO convertToDto(PrestamoDocument prestamoEntity) {
        return modelMapper.map(prestamoEntity, PrestamoDTO.class);
    }

    public PrestamoDocument convertToEntity(PrestamoDTO prestamoDTO) {
        return modelMapper.map(prestamoDTO, PrestamoDocument.class);
    }
}
