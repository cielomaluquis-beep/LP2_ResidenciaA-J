package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.NotificacionDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Notificacion;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.NotificacionRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements INotificacionService {
    private final NotificacionRepository repository;
    private final UsuarioRepository usuarioRepository;

    public NotificacionServiceImpl(NotificacionRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotificacionDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public NotificacionDTO crear(NotificacionDTO dto) {
        Notificacion e = new Notificacion();
        mapDtoToEntity(dto, e);
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<NotificacionDTO> actualizar(Long id, NotificacionDTO dto) {
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

    private void mapDtoToEntity(NotificacionDTO dto, Notificacion e) {
        e.setTipo(dto.tipo());
        e.setMensaje(dto.mensaje());
        e.setFechaCreacion(dto.fechaCreacion());
        e.setLeido(dto.leido());
        e.setUrlDestino(dto.urlDestino());

        if (dto.idUsuario() != null) {
            e.setUsuario(usuarioRepository.findById(dto.idUsuario()).orElse(null));
        } else {
            e.setUsuario(null);
        }
    }

    private NotificacionDTO convertToDTO(Notificacion e) {
        return new NotificacionDTO(
                e.getIdNotificacion(),
                e.getTipo(),
                e.getMensaje(),
                e.getFechaCreacion(),
                e.getLeido(),
                e.getUsuario() != null ? e.getUsuario().getIdUsuario() : null,
                e.getUrlDestino()
        );
    }
}
