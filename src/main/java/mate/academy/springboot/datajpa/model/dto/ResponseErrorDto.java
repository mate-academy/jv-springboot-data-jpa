package mate.academy.springboot.datajpa.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ResponseErrorDto {
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    public final LocalDateTime date;
    public final Integer errorCode;
    public final String message;

    public ResponseErrorDto(final Integer errorCode,
                            final String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.date = LocalDateTime.now(ZoneId.systemDefault());
    }
}
