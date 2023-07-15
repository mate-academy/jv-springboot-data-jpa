package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    private BigDecimal price;
    private String title;
    private Long categoryId;
}
