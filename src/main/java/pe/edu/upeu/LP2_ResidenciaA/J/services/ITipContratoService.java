package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.TipContratoDTO;
import java.util.List;
import java.util.Optional;
public interface ITipContratoService {
    List<TipContratoDTO> listarTodo();
    Optional<TipContratoDTO> buscarPorId(Long id);
    TipContratoDTO crear(TipContratoDTO dto);
    Optional<TipContratoDTO> actualizar(Long id, TipContratoDTO dto);
    boolean eliminar(Long id);
}
