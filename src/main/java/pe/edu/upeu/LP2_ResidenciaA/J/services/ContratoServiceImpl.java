package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ContratoDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.*;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements IContratoService {
    private final ContratoRepository repository;
    private final TipContratoRepository tipContratoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabitacionRepository habitacionRepository;

    public ContratoServiceImpl(ContratoRepository repository,
                               TipContratoRepository tipContratoRepository,
                               ClienteRepository clienteRepository,
                               UsuarioRepository usuarioRepository,
                               HabitacionRepository habitacionRepository) {
        this.repository = repository;
        this.tipContratoRepository = tipContratoRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratoDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContratoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public ContratoDTO crear(ContratoDTO dto) {
        Contrato e = new Contrato();
        mapDtoToEntity(dto, e);
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<ContratoDTO> actualizar(Long id, ContratoDTO dto) {
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

    private void mapDtoToEntity(ContratoDTO dto, Contrato e) {
        e.setFechaReserva(dto.fechaReserva());
        e.setFechaInicio(dto.fechaInicio());
        e.setFechaFin(dto.fechaFin());
        e.setMonto(dto.monto());
        e.setMetodoPago(dto.metodoPago());
        e.setImgPagoReserva(dto.imgPagoReserva());
        e.setEstado(dto.estado());

        if (dto.idTipoContrato() != null) {
            e.setTipContrato(tipContratoRepository.findById(dto.idTipoContrato()).orElse(null));
        } else {
            e.setTipContrato(null);
        }

        if (dto.idCliente() != null) {
            e.setCliente(clienteRepository.findById(dto.idCliente()).orElse(null));
        } else {
            e.setCliente(null);
        }

        if (dto.idUsuario() != null) {
            e.setUsuario(usuarioRepository.findById(dto.idUsuario()).orElse(null));
        } else {
            e.setUsuario(null);
        }

        if (dto.idHabitacion() != null) {
            e.setHabitacion(habitacionRepository.findById(dto.idHabitacion()).orElse(null));
        } else {
            e.setHabitacion(null);
        }
    }

    private ContratoDTO convertToDTO(Contrato e) {
        return new ContratoDTO(
                e.getIdContrato(),
                e.getTipContrato() != null ? e.getTipContrato().getIdTipoContrato() : null,
                e.getCliente() != null ? e.getCliente().getIdCliente() : null,
                e.getUsuario() != null ? e.getUsuario().getIdUsuario() : null,
                e.getHabitacion() != null ? e.getHabitacion().getIdHabitacion() : null,
                e.getFechaReserva(),
                e.getFechaInicio(),
                e.getFechaFin(),
                e.getMonto(),
                e.getMetodoPago(),
                e.getImgPagoReserva(),
                e.getEstado()
        );
    }
}
