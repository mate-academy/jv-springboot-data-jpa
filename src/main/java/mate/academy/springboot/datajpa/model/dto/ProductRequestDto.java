package mate.academy.springboot.datajpa.model.dto;

import java.math.BigDecimal;
import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductRequestDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Category category;

}
