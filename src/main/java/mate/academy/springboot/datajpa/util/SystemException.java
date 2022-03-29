package mate.academy.springboot.datajpa.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends RuntimeException {
    private final String message;

    public SystemException(String message) {
        super(message);
        this.message = message;
    }
}
