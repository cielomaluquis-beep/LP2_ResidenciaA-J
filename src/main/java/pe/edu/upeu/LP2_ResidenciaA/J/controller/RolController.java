package pe.edu.upeu.LP2_ResidenciaA.J.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.RolDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IRolService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {
    private final IRolService service;
    public RolController(IRolService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<RolDTO>> listar() { return ResponseEntity.ok(service.listarTodo()); }
    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
    }
    @PostMapping
    public ResponseEntity<RolDTO> crear(@Valid @RequestBody RolDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RolDTO dto) {
        return service.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Rol no encontrado con ID: " + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) { return ResponseEntity.noContent().build(); }
        throw new ResourceNotFoundException("No se pudo eliminar, Rol no encontrado con ID: " + id);
    }
}
