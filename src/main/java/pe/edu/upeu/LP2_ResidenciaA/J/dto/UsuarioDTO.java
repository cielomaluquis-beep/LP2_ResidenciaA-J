package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idUsuario,
        Long idPersona,
        Long idRol,
        String nombre,
        String descripcion,
        String estado,
        String contrasena
) {}

