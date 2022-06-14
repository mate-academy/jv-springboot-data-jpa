package mate.academy.springboot.datajpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ControllerException extends Exception{
    public ControllerException(String message) {
        super(message);
    }
}
