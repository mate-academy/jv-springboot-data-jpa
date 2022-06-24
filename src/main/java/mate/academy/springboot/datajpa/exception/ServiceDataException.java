package mate.academy.springboot.datajpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceDataException extends RuntimeException{
    public ServiceDataException(String message) {
        super(message);
    }
}
