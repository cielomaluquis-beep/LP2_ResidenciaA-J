package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public record PersonaDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idPersona,
        @NotBlank(message = "El nombre es obligatorio")
        String nombres,
        String apPaterno,
        String apMaterno,
        String nroDocumento,
        String telefono,
        String email,
        Date fechaNac
) {
}

