package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.RolDTO;
import java.util.List;
import java.util.Optional;
public interface IRolService {
    List<RolDTO> listarTodo();
    Optional<RolDTO> buscarPorId(Long id);
    RolDTO crear(RolDTO dto);
    Optional<RolDTO> actualizar(Long id, RolDTO dto);
    boolean eliminar(Long id);
}
