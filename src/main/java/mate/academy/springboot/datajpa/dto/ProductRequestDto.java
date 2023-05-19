package mate.academy.springboot.datajpa.dto;

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
    @Positive
    private Long categoryId;
}
