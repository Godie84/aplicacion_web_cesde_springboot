package com.cesde.cursos.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String mensaje;
    private final int codigo;
}
