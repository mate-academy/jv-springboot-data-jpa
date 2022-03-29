package mate.academy.springboot.datajpa.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Data;

@Data
public class ResponseErrorDto {
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private final LocalDateTime date;
    private final Integer errorCode;
    private final String message;

    public ResponseErrorDto(final Integer errorCode,
                            final String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.date = LocalDateTime.now(ZoneId.systemDefault());
    }
}
