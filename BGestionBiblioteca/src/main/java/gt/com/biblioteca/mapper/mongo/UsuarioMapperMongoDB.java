package gt.com.biblioteca.mapper.mongo;

import gt.com.biblioteca.dto.mongo.UsuarioDTO;
import gt.com.biblioteca.model.mongo.UsuarioDocument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapperMongoDB {
    private final ModelMapper modelMapper;

    public UsuarioDTO convertToDto(UsuarioDocument usuarioDocument) {
        return modelMapper.map(usuarioDocument, UsuarioDTO.class);
    }

    public UsuarioDocument convertToDocument(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, UsuarioDocument.class);
    }
}
