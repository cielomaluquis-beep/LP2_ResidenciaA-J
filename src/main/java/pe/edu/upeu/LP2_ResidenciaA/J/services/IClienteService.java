package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ClienteDTO;
import java.util.List;
import java.util.Optional;
public interface IClienteService {
    List<ClienteDTO> listarTodo();
    Optional<ClienteDTO> buscarPorId(Long id);
    ClienteDTO crear(ClienteDTO dto);
    Optional<ClienteDTO> actualizar(Long id, ClienteDTO dto);
    boolean eliminar(Long id);
}
