package gt.com.biblioteca.controller;

import gt.com.biblioteca.dto.postgre.LibroDTO;
import gt.com.biblioteca.service.ILibroServiceGeneric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libro")
public class LibroController {
    private final ILibroServiceGeneric iLibroServiceGeneric;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAllLibros() {
        List<LibroDTO> libroDTOListExist = iLibroServiceGeneric.getAllLibros();
        if (libroDTOListExist != null) {
            return ResponseEntity.ok(libroDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{libroId}")
    public ResponseEntity<Optional> getLibroById(@PathVariable("libroId") Long libroId) {
        Optional libroDTOExist = iLibroServiceGeneric.getLibroById(libroId);
        if (libroDTOExist != null) {
            return ResponseEntity.ok(libroDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<LibroDTO> createLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO libroDTONew = (LibroDTO) iLibroServiceGeneric.createLibro(libroDTO);
        if (libroDTONew != null) {
            return ResponseEntity.ok(libroDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<LibroDTO> updateLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO libroDTOExist = (LibroDTO) iLibroServiceGeneric.updateLibro(libroDTO);
        if (libroDTOExist != null) {
            return ResponseEntity.ok(libroDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{libroId}")
    public ResponseEntity<LibroDTO> deactiveLibroById(@PathVariable("libroId") Long libroId) {
        LibroDTO libroDTOExist = (LibroDTO) iLibroServiceGeneric.deactiveLibroById(libroId);
        if (libroDTOExist != null) {
            return ResponseEntity.ok(libroDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{libroId}")
    public ResponseEntity<LibroDTO> deleteLibroById(@PathVariable("libroId") Long libroId) {
        LibroDTO libroDTOExist = (LibroDTO) iLibroServiceGeneric.deleteLibroById(libroId);
        if (libroDTOExist != null) {
            return ResponseEntity.ok(libroDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
