package com.lovelace.eventsUserStories.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Método auxiliar para construir el ProblemDetail estándar
    private ProblemDetail buildProblemDetail(HttpStatus status, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);

        // Agregamos los campos requeridos por tu HU5
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("traceId", UUID.randomUUID().toString());

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problem = buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource not found");
        problem.setType(URI.create("https://eventsvenue-user-stories.com/errores/not-found"));
        return problem;
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicateResourceException(DuplicateResourceException ex) {
        ProblemDetail problem = buildProblemDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Resource conflict");
        return problem;
    }

    // Este maneja tus validaciones (incluyendo la de fechas que creamos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problem = buildProblemDetail(HttpStatus.BAD_REQUEST, "Error en la validación de datos");
        problem.setTitle("Invalid data");

        // Recopilar errores de campos específicos
        problem.setProperty("errors", ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .toList());

        // Recopilar errores globales (como nuestra validación de fechas cruzadas)
        if (ex.getBindingResult().hasGlobalErrors()) {
            problem.setProperty("globalErrors", ex.getBindingResult().getGlobalErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    .toList());
        }

        return problem;
    }
}