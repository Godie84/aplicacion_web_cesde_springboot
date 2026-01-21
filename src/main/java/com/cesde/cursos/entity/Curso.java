package com.cesde.cursos.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Integer duracionSemanas;
    private BigDecimal precio;
    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
}
