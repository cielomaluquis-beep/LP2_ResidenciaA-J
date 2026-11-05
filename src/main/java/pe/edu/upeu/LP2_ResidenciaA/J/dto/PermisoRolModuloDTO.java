package pe.edu.upeu.LP2_ResidenciaA.J.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PermisoRolModuloDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long idPermiso,
        Integer verModulo,
        Integer pView,
        Integer pCreate,
        Integer pEdit,
        Integer pDelete,
        Long idRol,
        Long idModulo
) {}

