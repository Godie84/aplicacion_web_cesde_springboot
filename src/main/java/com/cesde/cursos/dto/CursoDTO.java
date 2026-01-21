package com.cesde.cursos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de un curso")
public class CursoDTO {

    private Long id;

    @Schema(description = "Nombre del curso", example = "Java Backend")
    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    @Schema(description = "Descripción del curso", example = "Curso completo de Java")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Schema(description = "Duración en semanas", example = "8")
    @NotNull(message = "La duración es obligatoria")
    private Integer duracionSemanas;

    @Schema(description = "Precio del curso", example = "1200000")
    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @Schema(description = "Fecha de inicio", example = "2026-03-01T08:00:00")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @Schema(description = "Id del docente que dicta el curso", example = "1")
    @NotNull(message = "El docente es obligatorio")
    private Long docenteId;
}
