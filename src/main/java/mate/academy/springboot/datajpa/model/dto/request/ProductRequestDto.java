package mate.academy.springboot.datajpa.model.dto.request;

import java.math.BigDecimal;
import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category categoryId;
}
