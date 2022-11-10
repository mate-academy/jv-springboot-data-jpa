package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    @Size(min = 3)
    private String title;
    @Size(min = 1)
    private BigDecimal price;
    @NotNull
    private Long categoryId;
}
