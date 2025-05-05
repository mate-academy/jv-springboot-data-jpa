package mate.academy.springboot.datajpa.exception;

import java.time.ZonedDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class ApiException {
    private Exception exception;
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
}
