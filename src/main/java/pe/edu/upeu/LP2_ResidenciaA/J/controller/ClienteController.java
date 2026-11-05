package pe.edu.upeu.LP2_ResidenciaA.J.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ClienteDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.IClienteService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final IClienteService service;
    public ClienteController(IClienteService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() { return ResponseEntity.ok(service.listarTodo()); }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, Cliente no encontrado con ID: " + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) { return ResponseEntity.noContent().build(); }
        throw new ResourceNotFoundException("No se pudo eliminar, Cliente no encontrado con ID: " + id);
    }
}
