package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category category;
}
