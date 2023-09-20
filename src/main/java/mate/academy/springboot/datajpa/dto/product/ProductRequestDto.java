package mate.academy.springboot.datajpa.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @NotNull
    @Min(0)
    private BigDecimal price;
}
