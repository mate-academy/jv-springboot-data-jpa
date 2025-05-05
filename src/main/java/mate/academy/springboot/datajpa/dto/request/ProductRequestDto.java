package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import mate.academy.springboot.datajpa.model.Category;

@Getter
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category category;
}
