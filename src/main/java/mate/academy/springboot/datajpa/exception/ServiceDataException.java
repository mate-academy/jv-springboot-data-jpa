package mate.academy.springboot.datajpa.exception;

public class ServiceDataException extends RuntimeException{
    public ServiceDataException(String message) {
        super(message);
    }
}
