package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private Long categoryId;
}
