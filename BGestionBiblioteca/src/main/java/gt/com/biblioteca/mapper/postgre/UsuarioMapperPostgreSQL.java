package gt.com.biblioteca.mapper.postgre;

import gt.com.biblioteca.dto.postgre.UsuarioDTO;
import gt.com.biblioteca.model.postgre.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapperPostgreSQL {
    private final ModelMapper modelMapper;

    public UsuarioDTO convertToDto(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    public UsuarioEntity convertToEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, UsuarioEntity.class);
    }
}
