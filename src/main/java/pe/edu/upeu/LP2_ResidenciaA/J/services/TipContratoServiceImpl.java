package pe.edu.upeu.LP2_ResidenciaA.J.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.TipContratoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.*;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.TipContratoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TipContratoServiceImpl implements ITipContratoService {
    private final TipContratoRepository repository;
    public TipContratoServiceImpl(TipContratoRepository repository) { this.repository = repository; }

    @Override
    @Transactional(readOnly = true)
    public List<TipContratoDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<TipContratoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }
    @Override
    @Transactional
    public TipContratoDTO crear(TipContratoDTO dto) {
        TipContrato e = new TipContrato();
        e.setNombre(dto.nombre());
        e.setEstado(dto.estado());
        return convertToDTO(repository.save(e));
    }
    @Override
    @Transactional
    public Optional<TipContratoDTO> actualizar(Long id, TipContratoDTO dto) {
        return repository.findById(id).map(e -> {
        e.setNombre(dto.nombre());
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
    private TipContratoDTO convertToDTO(TipContrato e) {
        return new TipContratoDTO(
                e.getIdTipoContrato(),
                e.getNombre(),
                e.getEstado()
        );
    }
}
