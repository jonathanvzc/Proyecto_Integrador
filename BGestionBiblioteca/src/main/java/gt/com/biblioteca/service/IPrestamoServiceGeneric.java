package gt.com.biblioteca.service;

import gt.com.biblioteca.dto.postgre.PrestamoDTO;

import java.util.List;

public interface IPrestamoServiceGeneric<T, ID> {
    List<T> getAllPrestamos();

    T getPrestamoById(ID prestamoId);

    T createPrestamo(T prestamoDTO);

    T updatePrestamo(T prestamoDTO);

    T deactivePrestamoById(ID prestamoId);

    T deletePrestamoById(ID prestamoId);
}
