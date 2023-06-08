package mate.academy.springboot.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
