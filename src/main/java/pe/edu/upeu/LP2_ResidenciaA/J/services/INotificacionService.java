package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.NotificacionDTO;
import java.util.List;
import java.util.Optional;
public interface INotificacionService {
    List<NotificacionDTO> listarTodo();
    Optional<NotificacionDTO> buscarPorId(Long id);
    NotificacionDTO crear(NotificacionDTO dto);
    Optional<NotificacionDTO> actualizar(Long id, NotificacionDTO dto);
    boolean eliminar(Long id);
}
