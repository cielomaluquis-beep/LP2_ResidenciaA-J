package pe.edu.upeu.LP2_ResidenciaA.J.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ModuloDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.*;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.ModuloRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements IModuloService {
    private final ModuloRepository repository;
    public ModuloServiceImpl(ModuloRepository repository) { this.repository = repository; }

    @Override
    @Transactional(readOnly = true)
    public List<ModuloDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<ModuloDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }
    @Override
    @Transactional
    public ModuloDTO crear(ModuloDTO dto) {
        Modulo e = new Modulo();
        e.setNombre(dto.nombre());
        e.setRuta(dto.ruta());
        e.setIcono(dto.icono());
        e.setOrden(dto.orden());
        return convertToDTO(repository.save(e));
    }
    @Override
    @Transactional
    public Optional<ModuloDTO> actualizar(Long id, ModuloDTO dto) {
        return repository.findById(id).map(e -> {
        e.setNombre(dto.nombre());
        e.setRuta(dto.ruta());
        e.setIcono(dto.icono());
        e.setOrden(dto.orden());
            return convertToDTO(repository.save(e));
        });
    }
    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) { repository.deleteById(id); return true; }
        return false;
    }
    private ModuloDTO convertToDTO(Modulo e) {
        return new ModuloDTO(
                e.getIdModulo(),
                e.getNombre(),
                e.getRuta(),
                e.getIcono(),
                e.getOrden()
        );
    }
}
