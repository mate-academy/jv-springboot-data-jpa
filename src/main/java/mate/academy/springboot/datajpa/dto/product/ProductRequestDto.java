package mate.academy.springboot.datajpa.dto.product;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @NotNull
    @Min(0)
    private BigDecimal price;
}
