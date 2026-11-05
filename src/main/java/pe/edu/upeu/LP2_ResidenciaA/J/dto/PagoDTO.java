package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public record PagoDTO(
        @Parameter(hidden = true)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idPago,
        Long idContrato,
        Long idUsuario,
        Double monto,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fecha,
        String metodoPago,
        @Parameter(hidden = true)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String imgPago,
        String estado
) {}



