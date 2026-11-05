package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ContratoDTO;
import java.util.List;
import java.util.Optional;
public interface IContratoService {
    List<ContratoDTO> listarTodo();
    Optional<ContratoDTO> buscarPorId(Long id);
    ContratoDTO crear(ContratoDTO dto);
    Optional<ContratoDTO> actualizar(Long id, ContratoDTO dto);
    boolean eliminar(Long id);
}
