package mate.academy.springboot.datajpa.model.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryName;
}
