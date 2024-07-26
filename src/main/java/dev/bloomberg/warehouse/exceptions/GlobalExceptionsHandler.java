package dev.bloomberg.warehouse.exceptions;


import dev.bloomberg.warehouse.exceptions.customs.ModularException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest controller responsible for handling application exceptions, formatting error messages and sending them as a response to client
 */
@RestControllerAdvice
public class GlobalExceptionsHandler {
    /**
     * handles dtos validation errors, formats error messages and send them to the client
     *
     * @param ex @{@link MethodArgumentNotValidException} thrown from validation annotations in request dtos
     * @return ResponseEntity containing error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * handles modular exception thrown in modular service
     *
     * @param ex @{@link ModularException} contains error message and http status code
     * @return ResponseEntity containing error message
     */
    @ExceptionHandler(ModularException.class)
    public ResponseEntity<Map<String, String>> handleModularException(ModularException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), ex.getStatus());
    }
}

