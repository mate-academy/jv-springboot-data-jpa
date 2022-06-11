package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;

@Data
public class ResponseExceptionDto {
    private String message;

    public ResponseExceptionDto(String message) {
        this.message = message;
    }
}
