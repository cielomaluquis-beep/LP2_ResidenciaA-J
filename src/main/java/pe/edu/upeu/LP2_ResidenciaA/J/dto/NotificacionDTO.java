package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public record NotificacionDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idNotificacion,
        String tipo,
        String mensaje,
        Date fechaCreacion,
        Integer leido,
        Long idUsuario,
        String urlDestino
) {}

