package gt.com.biblioteca.mapper.mongo;

import gt.com.biblioteca.dto.mongo.PrestamoDTO;
import gt.com.biblioteca.model.mongo.PrestamoDocument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrestamoMapperMongoDB {
    private final ModelMapper modelMapper;

    public PrestamoDTO convertToDto(PrestamoDocument prestamoDocument) {
        return modelMapper.map(prestamoDocument, PrestamoDTO.class);
    }

    public PrestamoDocument convertToDocument(PrestamoDTO prestamoDTO) {
        return modelMapper.map(prestamoDTO, PrestamoDocument.class);
    }
}
