package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RolDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idRol,
        String nombre,
        String descripcion,
        String estado
) {}

