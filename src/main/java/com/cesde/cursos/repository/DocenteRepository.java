package com.cesde.cursos.repository;

import com.cesde.cursos.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    // Método opcional para buscar por documento
    Optional<Docente> findByDocumento(String documento);

    // Método opcional para buscar por correo
    Optional<Docente> findByCorreo(String correo);
}
