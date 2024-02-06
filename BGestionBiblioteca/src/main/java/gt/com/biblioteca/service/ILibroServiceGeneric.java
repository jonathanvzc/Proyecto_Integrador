package gt.com.biblioteca.service;

import gt.com.biblioteca.dto.postgre.LibroDTO;

import java.util.List;
import java.util.Optional;

public interface ILibroServiceGeneric<T, ID> {
    List<T> getAllLibros();

    Optional<T> getLibroById(ID libroId);

    T createLibro(T libroDTO);

    T updateLibro(T libroDTO);

    T deactiveLibroById(ID libroId);

    T deleteLibroById(ID libroId);
}
