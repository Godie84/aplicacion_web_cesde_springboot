package com.cesde.cursos.service;

import com.cesde.cursos.dto.DocenteDTO;
import com.cesde.cursos.entity.Docente;
import com.cesde.cursos.exception.NotFoundException;
import com.cesde.cursos.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public DocenteDTO crear(DocenteDTO dto) {
        Docente docente = dtoToEntity(dto);
        docente = docenteRepository.save(docente);
        return entityToDto(docente);
    }

    public List<DocenteDTO> listar() {
        return docenteRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public DocenteDTO actualizar(Long id, DocenteDTO dto) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Docente no encontrado"));

        docenteExistente.setNombre(dto.getNombre());
        docenteExistente.setDocumento(dto.getDocumento());
        docenteExistente.setCorreo(dto.getCorreo());

        docenteRepository.save(docenteExistente);

        return entityToDto(docenteExistente);
    }

    public void eliminar(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Docente no encontrado"));
        docenteRepository.delete(docente);
    }

    // Conversiones
    private Docente dtoToEntity(DocenteDTO dto) {
        return Docente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .documento(dto.getDocumento())
                .correo(dto.getCorreo())
                .build();
    }

    private DocenteDTO entityToDto(Docente docente) {
        return DocenteDTO.builder()
                .id(docente.getId())
                .nombre(docente.getNombre())
                .documento(docente.getDocumento())
                .correo(docente.getCorreo())
                .build();
    }
}
