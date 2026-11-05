package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PagoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Pago;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.ContratoRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.PagoRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements IPagoService {
    private final PagoRepository repository;
    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;

    public PagoServiceImpl(PagoRepository repository, ContratoRepository contratoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.contratoRepository = contratoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PagoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public PagoDTO crear(PagoDTO dto) {
        Pago e = new Pago();
        mapDtoToEntity(dto, e);
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<PagoDTO> actualizar(Long id, PagoDTO dto) {
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

    private void mapDtoToEntity(PagoDTO dto, Pago e) {
        e.setMonto(dto.monto());
        e.setFecha(dto.fecha());
        e.setMetodoPago(dto.metodoPago());
        e.setImgPago(dto.imgPago());
        e.setEstado(dto.estado());

        if (dto.idContrato() != null) {
            e.setContrato(contratoRepository.findById(dto.idContrato()).orElse(null));
        } else {
            e.setContrato(null);
        }

        if (dto.idUsuario() != null) {
            e.setUsuario(usuarioRepository.findById(dto.idUsuario()).orElse(null));
        } else {
            e.setUsuario(null);
        }
    }

    private PagoDTO convertToDTO(Pago e) {
        return new PagoDTO(
                e.getIdPago(),
                e.getContrato() != null ? e.getContrato().getIdContrato() : null,
                e.getUsuario() != null ? e.getUsuario().getIdUsuario() : null,
                e.getMonto(),
                e.getFecha(),
                e.getMetodoPago(),
                e.getImgPago(),
                e.getEstado()
        );
    }
}
