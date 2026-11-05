package pe.edu.upeu.LP2_ResidenciaA.J.services;

import pe.edu.upeu.LP2_ResidenciaA.J.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    List<PersonaDTO> listarTodo();
    Optional<PersonaDTO> buscarPorId(Long id);
    PersonaDTO crear(PersonaDTO dto);
    Optional<PersonaDTO> actualizar(Long id, PersonaDTO dto);
    boolean eliminar(Long id);
}
