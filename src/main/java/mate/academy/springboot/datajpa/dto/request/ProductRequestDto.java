package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Long categoryId;
}
