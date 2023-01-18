package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank
    private String title;
    @DecimalMin(value = "0.0")
    private BigDecimal price;
    @Min(1)
    private Long categoryId;
}
