package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ModuloDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idModulo,
        String nombre,
        String ruta,
        String icono,
        Integer orden
) {}

