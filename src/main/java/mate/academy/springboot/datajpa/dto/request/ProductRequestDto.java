package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private BigDecimal price;
    private Long categoryId;
}
