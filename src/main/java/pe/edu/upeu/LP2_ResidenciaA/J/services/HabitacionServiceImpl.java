package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.HabitacionDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Habitacion;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.HabitacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements IHabitacionService {

    private final HabitacionRepository habitacionRepository;

    public HabitacionServiceImpl(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionDTO> listarTodo() {
        return habitacionRepository.findAll().stream()
                .map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HabitacionDTO> buscarPorId(Long id) {
        return habitacionRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public HabitacionDTO crear(HabitacionDTO dto) {
        Habitacion h = new Habitacion();
        h.setNumero(dto.numero());
        h.setPiso(dto.piso());
        h.setDescripcion(dto.descripcion());
        h.setPrecio(dto.precio());
        h.setImgHabitacion(dto.imgHabitacion());
        h.setEstado(dto.estado());
        return convertToDTO(habitacionRepository.save(h));
    }

    @Override
    @Transactional
    public Optional<HabitacionDTO> actualizar(Long id, HabitacionDTO dto) {
        return habitacionRepository.findById(id).map(h -> {
            h.setNumero(dto.numero());
            h.setPiso(dto.piso());
            h.setDescripcion(dto.descripcion());
            h.setPrecio(dto.precio());
            h.setImgHabitacion(dto.imgHabitacion());
            h.setEstado(dto.estado());
            return convertToDTO(habitacionRepository.save(h));
        });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (habitacionRepository.existsById(id)) {
            habitacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private HabitacionDTO convertToDTO(Habitacion h) {
        return new HabitacionDTO(
                h.getIdHabitacion(),
                h.getNumero(),
                h.getPiso(),
                h.getDescripcion(),
                h.getPrecio(),
                h.getImgHabitacion(),
                h.getEstado()
        );
    }
}
