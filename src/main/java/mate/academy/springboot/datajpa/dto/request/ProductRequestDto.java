package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NonNull
    private String title;
    @Positive
    @NotNull
    private BigDecimal price;
    private Long categoryId;
}
