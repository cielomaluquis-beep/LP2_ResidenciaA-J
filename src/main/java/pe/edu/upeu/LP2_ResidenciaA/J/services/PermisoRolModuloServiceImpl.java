package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PermisoRolModuloDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.PermisoRolModulo;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.ModuloRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.PermisoRolModuloRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoRolModuloServiceImpl implements IPermisoRolModuloService {
    private final PermisoRolModuloRepository repository;
    private final RolRepository rolRepository;
    private final ModuloRepository moduloRepository;

    public PermisoRolModuloServiceImpl(PermisoRolModuloRepository repository, RolRepository rolRepository, ModuloRepository moduloRepository) {
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.moduloRepository = moduloRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermisoRolModuloDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoRolModuloDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public PermisoRolModuloDTO crear(PermisoRolModuloDTO dto) {
        PermisoRolModulo e = new PermisoRolModulo();
        mapDtoToEntity(dto, e);
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<PermisoRolModuloDTO> actualizar(Long id, PermisoRolModuloDTO dto) {
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

    private void mapDtoToEntity(PermisoRolModuloDTO dto, PermisoRolModulo e) {
        e.setVerModulo(dto.verModulo());
        e.setpView(dto.pView());
        e.setpCreate(dto.pCreate());
        e.setpEdit(dto.pEdit());
        e.setpDelete(dto.pDelete());

        if (dto.idRol() != null) {
            e.setRol(rolRepository.findById(dto.idRol()).orElse(null));
        } else {
            e.setRol(null);
        }

        if (dto.idModulo() != null) {
            e.setModulo(moduloRepository.findById(dto.idModulo()).orElse(null));
        } else {
            e.setModulo(null);
        }
    }

    private PermisoRolModuloDTO convertToDTO(PermisoRolModulo e) {
        return new PermisoRolModuloDTO(
                e.getIdPermiso(),
                e.getVerModulo(),
                e.getpView(),
                e.getpCreate(),
                e.getpEdit(),
                e.getpDelete(),
                e.getRol() != null ? e.getRol().getIdRol() : null,
                e.getModulo() != null ? e.getModulo().getIdModulo() : null
        );
    }
}
