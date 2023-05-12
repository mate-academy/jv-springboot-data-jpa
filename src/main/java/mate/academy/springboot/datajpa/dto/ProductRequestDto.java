package mate.academy.springboot.datajpa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductRequestDto {
    @NotNull
    private String title;
    @Min(0)
    private BigDecimal price;
    @Min(1)
    private Long categoryId;
}
