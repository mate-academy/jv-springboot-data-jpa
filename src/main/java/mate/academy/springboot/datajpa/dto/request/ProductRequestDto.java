package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long categoryId;
}
