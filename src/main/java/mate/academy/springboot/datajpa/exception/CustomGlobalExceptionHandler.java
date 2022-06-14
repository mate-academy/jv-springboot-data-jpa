package mate.academy.springboot.datajpa.exception;

import mate.academy.springboot.datajpa.dto.response.ResponseExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomGlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    private ResponseExceptionDto handleException(ControllerException e) {
        return new ResponseExceptionDto(e.getMessage());
    }
}
