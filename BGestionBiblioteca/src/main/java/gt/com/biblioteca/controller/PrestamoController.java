package gt.com.biblioteca.controller;

import gt.com.biblioteca.dto.postgre.PrestamoDTO;
import gt.com.biblioteca.service.IPrestamoServiceGeneric;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prestamo")
public class PrestamoController {
    private final IPrestamoServiceGeneric iPrestamoServiceGeneric;

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> getAllLibros() {
        List<PrestamoDTO> prestamoDTOListExist = iPrestamoServiceGeneric.getAllPrestamos();
        if (prestamoDTOListExist != null) {
            return ResponseEntity.ok(prestamoDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{prestamoId}")
    public ResponseEntity<PrestamoDTO> getPrestamoById(@PathVariable("prestamoId") Long prestamoId) {
        PrestamoDTO prestamoDTOExist = (PrestamoDTO) iPrestamoServiceGeneric.getPrestamoById(prestamoId);
        if (prestamoDTOExist != null) {
            return ResponseEntity.ok(prestamoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestamoDTO> createPrestamo(@RequestBody @Valid PrestamoDTO prestamoDTO) {
        PrestamoDTO prestamoDTONew = (PrestamoDTO) iPrestamoServiceGeneric.createPrestamo(prestamoDTO);
        if (prestamoDTONew != null) {
            return ResponseEntity.ok(prestamoDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<PrestamoDTO> updatePrestamo(@RequestBody @Valid PrestamoDTO prestamoDTO) {
        PrestamoDTO prestamoDTOExist = (PrestamoDTO) iPrestamoServiceGeneric.updatePrestamo(prestamoDTO);
        if (prestamoDTOExist != null) {
            return ResponseEntity.ok(prestamoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{prestamoId}")
    public ResponseEntity<PrestamoDTO> deactivePrestamoById(@PathVariable("prestamoId") Long prestamoId) {
        PrestamoDTO prestamoDTOExist = (PrestamoDTO) iPrestamoServiceGeneric.deactivePrestamoById(prestamoId);
        if (prestamoDTOExist != null) {
            return ResponseEntity.ok(prestamoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{prestamoId}")
    public ResponseEntity<PrestamoDTO> deletePrestamoById(@PathVariable("prestamoId") Long prestamoId) {
        PrestamoDTO prestamoDTOExist = (PrestamoDTO) iPrestamoServiceGeneric.deletePrestamoById(prestamoId);
        if (prestamoDTOExist != null) {
            return ResponseEntity.ok(prestamoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
