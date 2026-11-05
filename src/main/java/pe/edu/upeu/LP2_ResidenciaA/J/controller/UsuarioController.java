package pe.edu.upeu.LP2_ResidenciaA.J.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.UsuarioDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IUsuarioService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final IUsuarioService service;
    public UsuarioController(IUsuarioService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() { return ResponseEntity.ok(service.listarTodo()); }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return service.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Usuario no encontrado con ID: " + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) { return ResponseEntity.noContent().build(); }
        throw new ResourceNotFoundException("No se pudo eliminar, Usuario no encontrado con ID: " + id);
    }
}
