package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.LP2_ResidenciaA.J.dto.ClienteDTO;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Cliente;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.ClienteRepository;
import pe.edu.upeu.LP2_ResidenciaA.J.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository repository;
    private final PersonaRepository personaRepository;

    public ClienteServiceImpl(ClienteRepository repository, PersonaRepository personaRepository) { 
        this.repository = repository; 
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listarTodo() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente e = new Cliente();
        if (dto.idPersona() != null) {
            e.setPersona(personaRepository.findById(dto.idPersona()).orElse(null));
        }
        return convertToDTO(repository.save(e));
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> actualizar(Long id, ClienteDTO dto) {
        return repository.findById(id).map(e -> {
            if (dto.idPersona() != null) {
                e.setPersona(personaRepository.findById(dto.idPersona()).orElse(null));
            } else {
                e.setPersona(null);
            }
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

    private ClienteDTO convertToDTO(Cliente e) {
        return new ClienteDTO(
                e.getIdCliente(),
                e.getPersona() != null ? e.getPersona().getIdPersona() : null
        );
    }
}
