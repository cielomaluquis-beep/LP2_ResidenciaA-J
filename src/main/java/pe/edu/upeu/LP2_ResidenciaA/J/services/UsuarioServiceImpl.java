package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.UsuarioDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Usuario;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.PersonaRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.RolRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final UsuarioRepository repository;
    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository repository, PersonaRepository personaRepository, RolRepository rolRepository) {
        this.repository = repository;
        this.personaRepository = personaRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public UsuarioDTO crear(UsuarioDTO dto) {
        Usuario e = new Usuario();
        mapDtoToEntity(dto, e);
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> actualizar(Long id, UsuarioDTO dto) {
        return repository.findById(id).map(e -> {
            mapDtoToEntity(dto, e);
            return convertToDTO(repository.save(e));
        });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private void mapDtoToEntity(UsuarioDTO dto, Usuario e) {
        e.setNombre(dto.nombre());
        e.setDescripcion(dto.descripcion());
        e.setEstado(dto.estado());
        e.setContrasena(dto.contrasena());

        if (dto.idPersona() != null) {
            e.setPersona(personaRepository.findById(dto.idPersona()).orElse(null));
        } else {
            e.setPersona(null);
        }

        if (dto.idRol() != null) {
            e.setRol(rolRepository.findById(dto.idRol()).orElse(null));
        } else {
            e.setRol(null);
        }
    }

    private UsuarioDTO convertToDTO(Usuario e) {
        return new UsuarioDTO(
                e.getIdUsuario(),
                e.getPersona() != null ? e.getPersona().getIdPersona() : null,
                e.getRol() != null ? e.getRol().getIdRol() : null,
                e.getNombre(),
                e.getDescripcion(),
                e.getEstado(),
                e.getContrasena()
        );
    }
}
