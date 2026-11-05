package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;
public interface IUsuarioService {
    List<UsuarioDTO> listarTodo();
    Optional<UsuarioDTO> buscarPorId(Long id);
    UsuarioDTO crear(UsuarioDTO dto);
    Optional<UsuarioDTO> actualizar(Long id, UsuarioDTO dto);
    boolean eliminar(Long id);
}
