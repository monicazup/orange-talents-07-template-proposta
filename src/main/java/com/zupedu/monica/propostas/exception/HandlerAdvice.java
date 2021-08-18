package com.zupedu.monica.propostas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> manipulador(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Collection<String> messages = new ArrayList<>();
        for (ConstraintViolation c : constraintViolations
        ) {
            String message = "Campo: " + c.getPropertyPath().toString().toUpperCase() + " apresentou o seguinte erro: " + c.getMessage();
            messages.add(message);
        }
        ErroPadronizado erroPadronizado = new ErroPadronizado(messages);
        return ResponseEntity.badRequest().body(erroPadronizado);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manipulador(MethodArgumentNotValidException exception) {
        Collection<String> messages = new ArrayList<>();
        BindingResult bind = exception.getBindingResult();
        List<FieldError> errors = bind.getFieldErrors();
        errors.forEach(fieldError -> {
            String message = "Falha: valor de " + fieldError.getField() + " " + fieldError.getDefaultMessage();
            messages.add(message);

        });
        ErroPadronizado erroPadronizado = new ErroPadronizado(messages);
        return ResponseEntity.badRequest().body(erroPadronizado);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> manipulador(ApiException e) {

        Collection<String> messages = new ArrayList<>();
        messages.add(e.getReason());
        ErroPadronizado erroPadronizado = new ErroPadronizado(messages);

        return ResponseEntity.status(e.getHttpStatus()).body(erroPadronizado);
    }

}
