package gt.com.biblioteca.mapper.mongo;

import gt.com.biblioteca.dto.mongo.LibroDTO;
import gt.com.biblioteca.model.mongo.LibroDocument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LibroMapperMongoDB {
    private final ModelMapper modelMapper;

    public LibroDTO convertToDto(LibroDocument libroDocument) {
        return modelMapper.map(libroDocument, LibroDTO.class);
    }

    public LibroDocument convertToDocument(LibroDTO libroDTO) {
        return modelMapper.map(libroDTO, LibroDocument.class);
    }
}
