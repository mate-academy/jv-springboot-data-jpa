package mate.academy.springboot.datajpa.util;

import mate.academy.springboot.datajpa.model.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ResponseErrorDto> handleSystemExceptions(final SystemException e) {
        return new ResponseEntity<>(new ResponseErrorDto(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
