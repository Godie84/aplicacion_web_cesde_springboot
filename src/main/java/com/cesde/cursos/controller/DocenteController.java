package com.cesde.cursos.controller;

import com.cesde.cursos.dto.DocenteDTO;
import com.cesde.cursos.service.DocenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @Operation(summary = "Crear un docente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<DocenteDTO> crear(@Valid @RequestBody DocenteDTO dto) {
        return ResponseEntity.ok(docenteService.crear(dto));
    }


    @Operation(summary = "Listar docentes")
    @GetMapping
    public ResponseEntity<List<DocenteDTO>> listar() {
        return ResponseEntity.ok(docenteService.listar());
    }

    @Operation(summary = "Actualizar un docente por id")
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DocenteDTO dto) {

        return ResponseEntity.ok(docenteService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un docente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        docenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
