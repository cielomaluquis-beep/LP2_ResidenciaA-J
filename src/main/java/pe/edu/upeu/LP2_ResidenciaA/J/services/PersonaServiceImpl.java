package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.PersonaDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Persona;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO> listarTodo() {
        return personaRepository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonaDTO> buscarPorId(Long id) {
        return personaRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public PersonaDTO crear(PersonaDTO dto) {
        Persona per = new Persona();
        setEntityFields(per, dto);
        return convertToDTO(personaRepository.save(per));
    }

    @Override
    @Transactional
    public Optional<PersonaDTO> actualizar(Long id, PersonaDTO dto) {
        return personaRepository.findById(id).map(per -> {
            setEntityFields(per, dto);
            return convertToDTO(personaRepository.save(per));
        });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void setEntityFields(Persona per, PersonaDTO dto) {
        per.setNombres(dto.nombres());
        per.setApPaterno(dto.apPaterno());
        per.setApMaterno(dto.apMaterno());
        per.setNroDocumento(dto.nroDocumento());
        per.setTelefono(dto.telefono());
        per.setEmail(dto.email());
        per.setFechaNac(dto.fechaNac());
    }

    private PersonaDTO convertToDTO(Persona p) {
        return new PersonaDTO(
                p.getIdPersona(),
                p.getNombres(),
                p.getApPaterno(),
                p.getApMaterno(),
                p.getNroDocumento(),
                p.getTelefono(),
                p.getEmail(),
                p.getFechaNac()
        );
    }
}
