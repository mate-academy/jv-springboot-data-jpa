package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    private BigDecimal price;
    @NotNull
    private Long categoryId;
}
