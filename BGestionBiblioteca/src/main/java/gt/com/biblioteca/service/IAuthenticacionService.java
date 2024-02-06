package gt.com.biblioteca.service;

import gt.com.biblioteca.auth.RespuestaAuthenticacion;
import gt.com.biblioteca.auth.SolicitudAuthenticacion;
import gt.com.biblioteca.auth.SolicitudRegistro;

public interface IAuthenticacionService {
    RespuestaAuthenticacion registro(SolicitudRegistro solicitudRegistro);
    RespuestaAuthenticacion authenticado(SolicitudAuthenticacion solicitudAuthenticacion);
}
