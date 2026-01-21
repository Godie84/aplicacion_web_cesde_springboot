package com.cesde.cursos.service;

import com.cesde.cursos.dto.CursoDTO;
import com.cesde.cursos.entity.Curso;
import com.cesde.cursos.entity.Docente;
import com.cesde.cursos.exception.NotFoundException;
import com.cesde.cursos.repository.CursoRepository;
import com.cesde.cursos.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;

    public CursoService(CursoRepository cursoRepository, DocenteRepository docenteRepository) {
        this.cursoRepository = cursoRepository;
        this.docenteRepository = docenteRepository;
    }

    public CursoDTO crear(CursoDTO dto) {
        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("Docente no encontrado"));

        Curso curso = dtoToEntity(dto);
        curso.setDocente(docente);

        curso = cursoRepository.save(curso);
        return entityToDto(curso);
    }

    public List<CursoDTO> listar() {
        return cursoRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarPorNombre(String nombre) {
        return cursoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarPorDuracion(Integer duracion) {
        return cursoRepository.findByDuracionSemanas(duracion)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarPorPrecioMaximo(Double precio) {
        return cursoRepository.findByPrecioLessThanEqual(BigDecimal.valueOf(precio))
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarPorDocente(Long docenteId) {
        return cursoRepository.findByDocenteId(docenteId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public CursoDTO actualizar(Long id, CursoDTO dto) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("Docente no encontrado"));

        cursoExistente.setNombre(dto.getNombre());
        cursoExistente.setDescripcion(dto.getDescripcion());
        cursoExistente.setDuracionSemanas(dto.getDuracionSemanas());
        cursoExistente.setPrecio(dto.getPrecio());
        cursoExistente.setFechaInicio(dto.getFechaInicio());
        cursoExistente.setDocente(docente);

        cursoRepository.save(cursoExistente);

        return entityToDto(cursoExistente);
    }

    public void eliminar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));
        cursoRepository.delete(curso);
    }

    // Conversiones
    private Curso dtoToEntity(CursoDTO dto) {
        return Curso.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .duracionSemanas(dto.getDuracionSemanas())
                .precio(dto.getPrecio())
                .fechaInicio(dto.getFechaInicio())
                .build();
    }

    private CursoDTO entityToDto(Curso curso) {
        return CursoDTO.builder()
                .id(curso.getId())
                .nombre(curso.getNombre())
                .descripcion(curso.getDescripcion())
                .duracionSemanas(curso.getDuracionSemanas())
                .precio(curso.getPrecio())
                .fechaInicio(curso.getFechaInicio())
                .docenteId(curso.getDocente().getId())
                .build();
    }
}
