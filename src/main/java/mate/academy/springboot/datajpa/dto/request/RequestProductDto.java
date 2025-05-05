package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class RequestProductDto {
    @NotNull
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Long categoryid;
}
