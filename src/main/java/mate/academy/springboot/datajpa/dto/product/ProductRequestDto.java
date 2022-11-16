package mate.academy.springboot.datajpa.dto.product;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private int price;
    private Long categoryId;
}
