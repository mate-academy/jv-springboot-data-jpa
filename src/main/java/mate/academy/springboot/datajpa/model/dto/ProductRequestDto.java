package mate.academy.springboot.datajpa.model.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductRequestDto {
    @NonNull
    private String title;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Long categoryId;
}
