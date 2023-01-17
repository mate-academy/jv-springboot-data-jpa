package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mate.academy.springboot.datajpa.model.Category;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Category category;
}
