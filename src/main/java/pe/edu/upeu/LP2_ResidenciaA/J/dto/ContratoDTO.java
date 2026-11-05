package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public record ContratoDTO(
        @Parameter(hidden = true)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idContrato,
        Long idTipoContrato,
        Long idCliente,
        Long idUsuario,
        Long idHabitacion,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fechaReserva,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fechaInicio,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fechaFin,
        Double monto,
        String metodoPago,
        @Parameter(hidden = true)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String imgPagoReserva,
        String estado
) {}



