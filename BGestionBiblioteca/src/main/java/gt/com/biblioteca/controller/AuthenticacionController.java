package gt.com.biblioteca.controller;

import gt.com.biblioteca.auth.RespuestaAuthenticacion;
import gt.com.biblioteca.auth.SolicitudAuthenticacion;
import gt.com.biblioteca.auth.SolicitudRegistro;
import gt.com.biblioteca.service.IAuthenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthenticacionController {
    private final IAuthenticacionService iAuthenticacionService;

    @PostMapping("registro")
    public ResponseEntity<RespuestaAuthenticacion> registro(@Valid @RequestBody SolicitudRegistro solicitudRegistro) {
        if (solicitudRegistro != null) {
            return ResponseEntity.ok(iAuthenticacionService.registro(solicitudRegistro));
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("autenticar")
    public ResponseEntity<RespuestaAuthenticacion> authenticado(@Valid @RequestBody SolicitudAuthenticacion solicitudAuthenticacion) {
        if (solicitudAuthenticacion != null) {
            return ResponseEntity.ok(iAuthenticacionService.authenticado(solicitudAuthenticacion));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
