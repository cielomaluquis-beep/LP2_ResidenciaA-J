package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TipContratoDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idTipoContrato,
        String nombre,
        String estado
) {}

