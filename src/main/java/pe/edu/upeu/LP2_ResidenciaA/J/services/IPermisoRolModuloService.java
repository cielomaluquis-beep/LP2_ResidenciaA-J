package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PermisoRolModuloDTO;
import java.util.List;
import java.util.Optional;
public interface IPermisoRolModuloService {
    List<PermisoRolModuloDTO> listarTodo();
    Optional<PermisoRolModuloDTO> buscarPorId(Long id);
    PermisoRolModuloDTO crear(PermisoRolModuloDTO dto);
    Optional<PermisoRolModuloDTO> actualizar(Long id, PermisoRolModuloDTO dto);
    boolean eliminar(Long id);
}
