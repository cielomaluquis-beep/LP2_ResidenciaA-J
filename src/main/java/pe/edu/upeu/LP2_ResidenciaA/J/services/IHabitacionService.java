package pe.edu.upeu.LP2_ResidenciaA.J.services;

import pe.edu.upeu.LP2_ResidenciaA.J.dto.HabitacionDTO;

import java.util.List;
import java.util.Optional;

public interface IHabitacionService {
    List<HabitacionDTO> listarTodo();
    Optional<HabitacionDTO> buscarPorId(Long id);
    HabitacionDTO crear(HabitacionDTO h);
    Optional<HabitacionDTO> actualizar(Long id, HabitacionDTO h);
    boolean eliminar(Long id);
}
