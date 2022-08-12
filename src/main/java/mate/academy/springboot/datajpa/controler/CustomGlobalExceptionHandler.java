package mate.academy.springboot.datajpa.controler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<Class<? extends RuntimeException>, HttpStatus> RESPONSE_STATUS_MAP;
    private static final HttpStatus DEFAULT_RESPONSE_STATUS = HttpStatus.CONFLICT;

    static {
        RESPONSE_STATUS_MAP = new HashMap<>();
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleConflict(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", getExceptionResponseStatus(ex.getClass()).value());
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body,
                new HttpHeaders(),
                getExceptionResponseStatus(ex.getClass()));
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            return field + " " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }

    private HttpStatus getExceptionResponseStatus(Class<? extends RuntimeException> clazz) {
        return RESPONSE_STATUS_MAP.getOrDefault(clazz, DEFAULT_RESPONSE_STATUS);
    }
}
