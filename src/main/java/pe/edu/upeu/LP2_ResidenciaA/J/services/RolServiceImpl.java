package pe.edu.upeu.LP2_ResidenciaA.J.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.RolDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.*;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.RolRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {
    private final RolRepository repository;
    public RolServiceImpl(RolRepository repository) { this.repository = repository; }

    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }
    @Override
    @Transactional
    public RolDTO crear(RolDTO dto) {
        Rol e = new Rol();
        e.setNombre(dto.nombre());
        e.setDescripcion(dto.descripcion());
        e.setEstado(dto.estado());
        return convertToDTO(repository.save(e));
    }
    @Override
    @Transactional
    public Optional<RolDTO> actualizar(Long id, RolDTO dto) {
        return repository.findById(id).map(e -> {
        e.setNombre(dto.nombre());
        e.setDescripcion(dto.descripcion());
        e.setEstado(dto.estado());
            return convertToDTO(repository.save(e));
        });
    }
    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) { repository.deleteById(id); return true; }
        return false;
    }
    private RolDTO convertToDTO(Rol e) {
        return new RolDTO(
                e.getIdRol(),
                e.getNombre(),
                e.getDescripcion(),
                e.getEstado()
        );
    }
}
