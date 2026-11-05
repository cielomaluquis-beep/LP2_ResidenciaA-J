package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PagoDTO;
import java.util.List;
import java.util.Optional;
public interface IPagoService {
    List<PagoDTO> listarTodo();
    Optional<PagoDTO> buscarPorId(Long id);
    PagoDTO crear(PagoDTO dto);
    Optional<PagoDTO> actualizar(Long id, PagoDTO dto);
    boolean eliminar(Long id);
}
