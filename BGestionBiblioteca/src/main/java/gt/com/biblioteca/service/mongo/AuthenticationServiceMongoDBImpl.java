package gt.com.biblioteca.service.mongo;

import gt.com.biblioteca.auth.RespuestaAuthenticacion;
import gt.com.biblioteca.auth.SolicitudAuthenticacion;
import gt.com.biblioteca.auth.SolicitudRegistro;
import gt.com.biblioteca.dto.mongo.UsuarioDTO;
import gt.com.biblioteca.mapper.mongo.UsuarioMapperMongoDB;
import gt.com.biblioteca.model.mongo.UsuarioDocument;
import gt.com.biblioteca.repository.mongo.IUsuarioRepositoryMongoDB;
import gt.com.biblioteca.service.IAuthenticacionService;
import gt.com.biblioteca.util.RolEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceMongoDBImpl implements IAuthenticacionService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IJwtServiceMongoDB iJwtServiceMongoDB;
    private final UsuarioMapperMongoDB usuarioMapperMongoDB;
    private final IUsuarioRepositoryMongoDB iUsuarioRepositoryMongoDB;

    @Override
    @Transactional
    public RespuestaAuthenticacion registro(SolicitudRegistro solicitudRegistro) {
        var usuarioDTO = UsuarioDTO.builder()
                .nombre(solicitudRegistro.getNombre())
                .apellido(solicitudRegistro.getApellido())
                .genero(solicitudRegistro.getGenero())
                .fechaNacimiento(solicitudRegistro.getFechaNacimiento())
                .dpi(solicitudRegistro.getDpi())
                .direccion(solicitudRegistro.getDireccion())
                .telefono(solicitudRegistro.getTelefono())
                .correoElectronico(solicitudRegistro.getCorreoElectronico())
                .contrasenia(passwordEncoder.encode(solicitudRegistro.getContrasenia()))
                .rol(RolEnum.SUPER_ADMINISTRADOR)
                .fechaCreacion(LocalDate.now())
                .fechaModificacion(null)
                .estado(true)
                .build();
        UsuarioDocument usuarioEntity = usuarioMapperMongoDB.convertToDocument(usuarioDTO);
        iUsuarioRepositoryMongoDB.save(usuarioEntity);
        var jwtToken = iJwtServiceMongoDB.generateToken(usuarioEntity);
        return RespuestaAuthenticacion.builder()
                .jwtToken(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public RespuestaAuthenticacion authenticado(SolicitudAuthenticacion solicitudAuthenticacion) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        solicitudAuthenticacion.getCorreoElectronico(),
                        solicitudAuthenticacion.getContrasenia()
                )
        );
        var usuario = iUsuarioRepositoryMongoDB.findByCorreoElectronicoAndEstadoIsTrue(solicitudAuthenticacion.getCorreoElectronico()).orElseThrow();
        var jwtToken = iJwtServiceMongoDB.generateToken(usuario);
        return RespuestaAuthenticacion.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
