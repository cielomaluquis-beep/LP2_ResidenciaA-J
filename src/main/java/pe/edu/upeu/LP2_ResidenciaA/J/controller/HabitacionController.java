package pe.edu.upeu.LP2_ResidenciaA.J.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.HabitacionDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IFileStorageService;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IHabitacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habitaciones")
public class HabitacionController {

    private final IHabitacionService habitacionService;
    private final IFileStorageService storageService;

    public HabitacionController(IHabitacionService habitacionService, IFileStorageService storageService) {
        this.habitacionService = habitacionService;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>> listar() {
        return ResponseEntity.ok(habitacionService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> obtener(@PathVariable Long id) {
        return habitacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Habitacion no encontrada con ID: " + id));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<HabitacionDTO> crear(
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid HabitacionDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        String nombreImagen = "no-image.png";
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgHabitacion() != null && !dto.imgHabitacion().isBlank()) {
            nombreImagen = dto.imgHabitacion();
        }

        HabitacionDTO conImagen = new HabitacionDTO(
                null,
                dto.numero(),
                dto.piso(),
                dto.descripcion(),
                dto.precio(),
                nombreImagen,
                dto.estado()
        );

        return new ResponseEntity<>(habitacionService.crear(conImagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<HabitacionDTO> actualizar(
            @PathVariable Long id,
            @ModelAttribute @org.springdoc.core.annotations.ParameterObject @Valid HabitacionDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        HabitacionDTO habitacionActual = habitacionService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habitacion no encontrada con ID: " + id));

        String nombreImagen = habitacionActual.imgHabitacion();
        if (file != null && !file.isEmpty()) {
            nombreImagen = storageService.store(file);
        } else if (dto.imgHabitacion() != null && !dto.imgHabitacion().isBlank()) {
            nombreImagen = dto.imgHabitacion();
        }

        HabitacionDTO dtoActualizado = new HabitacionDTO(
                id,
                dto.numero(),
                dto.piso(),
                dto.descripcion(),
                dto.precio(),
                nombreImagen,
                dto.estado()
        );

        return habitacionService.actualizar(id, dtoActualizado)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Habitacion con ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (habitacionService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("No se pudo eliminar, Habitacion no encontrada con ID: " + id);
    }
}
