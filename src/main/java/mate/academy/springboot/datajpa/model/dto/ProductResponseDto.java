package mate.academy.springboot.datajpa.model.dto;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Category category;

}
