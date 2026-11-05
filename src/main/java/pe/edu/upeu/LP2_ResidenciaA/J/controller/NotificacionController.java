package pe.edu.upeu.LP2_ResidenciaA.J.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.NotificacionDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.INotificacionService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    private final INotificacionService service;
    public NotificacionController(INotificacionService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<NotificacionDTO>> listar() { return ResponseEntity.ok(service.listarTodo()); }
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Notificacion no encontrado con ID: " + id));
    }
    @PostMapping
    public ResponseEntity<NotificacionDTO> crear(@Valid @RequestBody NotificacionDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionDTO dto) {
        return service.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Notificacion no encontrado con ID: " + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) { return ResponseEntity.noContent().build(); }
        throw new ResourceNotFoundException("No se pudo eliminar, Notificacion no encontrado con ID: " + id);
    }
}
