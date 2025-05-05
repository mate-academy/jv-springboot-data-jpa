package mate.academy.springboot.datajpa.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
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
