package pe.edu.upeu.LP2_ResidenciaA.J.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ContratoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IContratoService;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IFileStorageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos")
public class ContratoController {

    private final IContratoService service;
    private final IFileStorageService storageService;

    public ContratoController(IContratoService service, IFileStorageService storageService) {
        this.service = service;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<ContratoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con ID: " + id));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ContratoDTO> crear(
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid ContratoDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        String nombreImagen = "no-image.png";
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgPagoReserva() != null && !dto.imgPagoReserva().isBlank()) {
            nombreImagen = dto.imgPagoReserva();
        }

        ContratoDTO conImagen = new ContratoDTO(
                null,
                dto.idTipoContrato(),
                dto.idCliente(),
                dto.idUsuario(),
                dto.idHabitacion(),
                dto.fechaReserva(),
                dto.fechaInicio(),
                dto.fechaFin(),
                dto.monto(),
                dto.metodoPago(),
                nombreImagen,
                dto.estado()
        );

        return new ResponseEntity<>(service.crear(conImagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ContratoDTO> actualizar(
            @PathVariable Long id,
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid ContratoDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        ContratoDTO actual = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con ID: " + id));

        String nombreImagen = actual.imgPagoReserva();
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgPagoReserva() != null && !dto.imgPagoReserva().isBlank()) {
            nombreImagen = dto.imgPagoReserva();
        }

        ContratoDTO dtoActualizado = new ContratoDTO(
                id,
                dto.idTipoContrato(),
                dto.idCliente(),
                dto.idUsuario(),
                dto.idHabitacion(),
                dto.fechaReserva(),
                dto.fechaInicio(),
                dto.fechaFin(),
                dto.monto(),
                dto.metodoPago(),
                nombreImagen,
                dto.estado()
        );

        return service.actualizar(id, dtoActualizado)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Contrato no encontrado con ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("No se pudo eliminar, Contrato no encontrado con ID: " + id);
    }
}
