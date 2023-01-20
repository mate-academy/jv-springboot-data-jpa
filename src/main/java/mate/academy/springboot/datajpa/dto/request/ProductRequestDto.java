package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Long categoryId;
}
