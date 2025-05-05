package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ProductRequestDto {
    @NonNull
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
