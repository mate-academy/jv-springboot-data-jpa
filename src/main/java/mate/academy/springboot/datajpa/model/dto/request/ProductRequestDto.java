package mate.academy.springboot.datajpa.model.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String title;
    @Min(0)
    private BigDecimal price;
    @Min(0)
    private Long categoryId;
}
