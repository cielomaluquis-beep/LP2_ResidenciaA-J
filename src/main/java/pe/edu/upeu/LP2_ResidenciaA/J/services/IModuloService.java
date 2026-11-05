package pe.edu.upeu.LP2_ResidenciaA.J.services;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ModuloDTO;
import java.util.List;
import java.util.Optional;
public interface IModuloService {
    List<ModuloDTO> listarTodo();
    Optional<ModuloDTO> buscarPorId(Long id);
    ModuloDTO crear(ModuloDTO dto);
    Optional<ModuloDTO> actualizar(Long id, ModuloDTO dto);
    boolean eliminar(Long id);
}
