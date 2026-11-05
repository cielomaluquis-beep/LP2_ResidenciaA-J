package pe.edu.upeu.LP2_ResidenciaA.J.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PagoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IFileStorageService;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final IPagoService service;
    private final IFileStorageService storageService;

    public PagoController(IPagoService service, IFileStorageService storageService) {
        this.service = service;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PagoDTO> crear(
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid PagoDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        String nombreImagen = "no-image.png";
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgPago() != null && !dto.imgPago().isBlank()) {
            nombreImagen = dto.imgPago();
        }

        PagoDTO conImagen = new PagoDTO(
                null,
                dto.idContrato(),
                dto.idUsuario(),
                dto.monto(),
                dto.fecha(),
                dto.metodoPago(),
                nombreImagen,
                dto.estado()
        );

        return new ResponseEntity<>(service.crear(conImagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PagoDTO> actualizar(
            @PathVariable Long id,
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid PagoDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        PagoDTO actual = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));

        String nombreImagen = actual.imgPago();
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgPago() != null && !dto.imgPago().isBlank()) {
            nombreImagen = dto.imgPago();
        }

        PagoDTO dtoActualizado = new PagoDTO(
                id,
                dto.idContrato(),
                dto.idUsuario(),
                dto.monto(),
                dto.fecha(),
                dto.metodoPago(),
                nombreImagen,
                dto.estado()
        );

        return service.actualizar(id, dtoActualizado)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Pago no encontrado con ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("No se pudo eliminar, Pago no encontrado con ID: " + id);
    }
}
