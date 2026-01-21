package com.cesde.cursos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de un docente")
public class DocenteDTO {

    private Long id;

    @Schema(description = "Nombre completo del docente", example = "Carlos Jimenez")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(description = "Número de documento", example = "123456789")
    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @Schema(description = "Correo electrónico", example = "juan@cesde.edu.co")
    @Email(message = "El correo debe ser válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
}
