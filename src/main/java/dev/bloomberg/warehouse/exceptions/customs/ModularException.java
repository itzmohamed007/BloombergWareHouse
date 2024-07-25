package dev.bloomberg.warehouse.exceptions.customs;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Modular exception thrown in modular service to display error message with their http status
 */
@Getter
public class ModularException extends RuntimeException {
    private final HttpStatus status;

    public ModularException(String message, HttpStatus httpStatus) {
        super(message);
        status = httpStatus;
    }
}
