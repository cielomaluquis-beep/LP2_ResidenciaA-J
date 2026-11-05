package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public record HabitacionDTO(
        @Parameter(hidden = true)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idHabitacion,
        String numero,
        Integer piso,
        String descripcion,
        Double precio,
        @Parameter(hidden = true)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String imgHabitacion,
        String estado
) {}


