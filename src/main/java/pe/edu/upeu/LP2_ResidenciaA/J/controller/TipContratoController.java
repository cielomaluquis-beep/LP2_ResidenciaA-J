package pe.edu.upeu.LP2_ResidenciaA.J.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.TipContratoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.exception.ResourceNotFoundException;
import pe.edu.upeu.LP2_ResidenciaA.J.services.ITipContratoService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-contratos")
public class TipContratoController {
    private final ITipContratoService service;
    public TipContratoController(ITipContratoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<TipContratoDTO>> listar() { return ResponseEntity.ok(service.listarTodo()); }
    @GetMapping("/{id}")
    public ResponseEntity<TipContratoDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("TipContrato no encontrado con ID: " + id));
    }
    @PostMapping
    public ResponseEntity<TipContratoDTO> crear(@Valid @RequestBody TipContratoDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TipContratoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TipContratoDTO dto) {
        return service.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, TipContrato no encontrado con ID: " + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) { return ResponseEntity.noContent().build(); }
        throw new ResourceNotFoundException("No se pudo eliminar, TipContrato no encontrado con ID: " + id);
    }
}
