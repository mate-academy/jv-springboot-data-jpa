package mate.academy.springboot.datajpa.exception;

import mate.academy.springboot.datajpa.dto.response.ResponseExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomGlobalExceptionHandler {

    @ExceptionHandler(ServiceDataException.class)
    private ResponseExceptionDto handleException(ServiceDataException e) {
        return new ResponseExceptionDto(e.getMessage());
    }
}
