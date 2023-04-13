package mate.academy.springboot.datajpa.dto.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
