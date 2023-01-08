package mate.academy.springboot.datajpa.controller;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<?> handleEntityNotFound(
            RuntimeException e,
            HttpHeaders headers
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map<String, ?> response = Map.of(
                "timestamp", new Date(),
                "status", status,
                "message", e.getMessage()
        );
        return new ResponseEntity<>(response, headers, status);
    }
}
