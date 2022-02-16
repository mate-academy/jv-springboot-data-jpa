package mate.academy.springboot.datajpa.model.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
