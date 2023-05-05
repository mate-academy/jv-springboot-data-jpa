package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import mate.academy.springboot.datajpa.model.Category;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category category;
}
