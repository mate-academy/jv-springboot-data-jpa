package mate.academy.springboot.datajpa.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleEntityNotFoundException(Exception ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException()
                .setMessage(ex.getMessage())
                .setHttpStatus(badRequest)
                .setException(ex)
                .setZonedDateTime(ZonedDateTime.now(ZoneId.of("Europe/Kiev")));
        return new ResponseEntity<>(apiException, badRequest);
    }
}
