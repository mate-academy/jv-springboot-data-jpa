package mate.academy.springboot.datajpa.model.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NotEmpty
    private String title;
    @Min(value = 0)
    private BigDecimal price;
    private Long categoryId;
}
