package gt.com.biblioteca;

import gt.com.biblioteca.auth.RespuestaAuthenticacion;
import gt.com.biblioteca.auth.SolicitudAuthenticacion;
import gt.com.biblioteca.auth.SolicitudRegistro;
import gt.com.biblioteca.controller.AuthenticacionController;
import gt.com.biblioteca.service.IAuthenticacionService;
import gt.com.biblioteca.util.GeneroEnum;
import gt.com.biblioteca.util.RolEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticacionController controller;

    @Mock
    private IAuthenticacionService iAuthenticacionService;

    @Test
    public void testRegistro_ok() {
        SolicitudRegistro solicitudRegistro = new SolicitudRegistro();
        solicitudRegistro.setNombre("Juan Pérez");
        solicitudRegistro.setApellido("López García");
        solicitudRegistro.setGenero(GeneroEnum.Masculino);
        solicitudRegistro.setFechaNacimiento(LocalDate.of(1999, 7, 1));
        solicitudRegistro.setDpi("1010253451023");
        solicitudRegistro.setDireccion("San Felipe, Retalhuleu");
        solicitudRegistro.setTelefono("+50253597506");
        solicitudRegistro.setCorreoElectronico("juan.perez@gmail.com");
        solicitudRegistro.setContrasenia("123456");
        solicitudRegistro.setRol(RolEnum.SUPER_ADMINISTRADOR);
        solicitudRegistro.setFechaCreacion(LocalDate.now());
        solicitudRegistro.setFechaModificacion(null);
        solicitudRegistro.setEstado(true);
        RespuestaAuthenticacion respuestaEsperada = new RespuestaAuthenticacion();
        when(iAuthenticacionService.registro(solicitudRegistro)).thenReturn(respuestaEsperada);
        ResponseEntity<RespuestaAuthenticacion> respuesta = controller.registro(solicitudRegistro);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Assertions.assertEquals(respuestaEsperada, respuesta.getBody());
    }

    @Test
    public void testAuthenticado_ok() {
        SolicitudAuthenticacion solicitudAuthenticacion = new SolicitudAuthenticacion();
        solicitudAuthenticacion.setCorreoElectronico("gersontajiboy8@gmail.com");
        solicitudAuthenticacion.setContrasenia("Desarrollador java");
        RespuestaAuthenticacion respuestaEsperada = new RespuestaAuthenticacion();
        respuestaEsperada.setJwtToken("token_generado");
        when(iAuthenticacionService.authenticado(solicitudAuthenticacion)).thenReturn(respuestaEsperada);
        ResponseEntity<RespuestaAuthenticacion> respuesta = controller.authenticado(solicitudAuthenticacion);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Assertions.assertEquals(respuestaEsperada, respuesta.getBody());
    }
}
