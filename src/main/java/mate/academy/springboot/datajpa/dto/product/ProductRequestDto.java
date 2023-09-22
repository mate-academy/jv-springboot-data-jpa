package mate.academy.springboot.datajpa.dto.product;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Long categoryId;
}
