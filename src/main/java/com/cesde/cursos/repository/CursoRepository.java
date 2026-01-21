package com.cesde.cursos.repository;

import com.cesde.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Filtros requeridos
    List<Curso> findByNombreContainingIgnoreCase(String nombre);

    List<Curso> findByDuracionSemanas(Integer duracion);

    List<Curso> findByPrecioLessThanEqual(BigDecimal precio);

    // Filtro adicional por docente
    List<Curso> findByDocenteId(Long docenteId);
}
