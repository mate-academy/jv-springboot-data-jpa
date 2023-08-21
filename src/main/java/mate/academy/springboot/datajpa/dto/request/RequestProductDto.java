package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class RequestProductDto {
    @NotBlank
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Long categoryId;
}
