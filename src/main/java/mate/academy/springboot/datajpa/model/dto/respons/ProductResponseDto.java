package mate.academy.springboot.datajpa.model.dto.respons;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
