package mate.academy.springboot.datajpa.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @Min(0)
    private BigDecimal price;
    private Long categoryId;
}
