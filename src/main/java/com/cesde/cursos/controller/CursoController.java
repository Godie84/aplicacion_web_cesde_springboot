package com.cesde.cursos.controller;

import com.cesde.cursos.dto.CursoDTO;
import com.cesde.cursos.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Crear un curso")
    @PostMapping
    public ResponseEntity<CursoDTO> crear(@Valid @RequestBody CursoDTO dto) {
        return ResponseEntity.ok(cursoService.crear(dto));
    }

    @Operation(summary = "Listar cursos")
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer duracion,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Long docenteId
    ) {
        // Si no hay filtros, retorna todo
        if (nombre == null && duracion == null && precioMax == null && docenteId == null) {
            return ResponseEntity.ok(cursoService.listar());
        }

        // Aplicar filtros
        if (nombre != null) {
            return ResponseEntity.ok(cursoService.buscarPorNombre(nombre));
        }

        if (duracion != null) {
            return ResponseEntity.ok(cursoService.buscarPorDuracion(duracion));
        }

        if (precioMax != null) {
            return ResponseEntity.ok(cursoService.buscarPorPrecioMaximo(precioMax));
        }

        if (docenteId != null) {
            return ResponseEntity.ok(cursoService.buscarPorDocente(docenteId));
        }

        return ResponseEntity.ok(cursoService.listar());
    }

    @Operation(summary = "Actualizar un curso por id")
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CursoDTO dto) {
        return ResponseEntity.ok(cursoService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un curso por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
