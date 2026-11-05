package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idCliente,
        Long idPersona
) {}

