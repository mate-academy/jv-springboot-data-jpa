package mate.academy.springboot.datajpa.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private BigDecimal price;
    private String title;
    private Long categoriesId;
}
