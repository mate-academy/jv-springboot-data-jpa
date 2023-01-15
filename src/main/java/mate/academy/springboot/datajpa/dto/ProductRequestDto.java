package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductRequestDto {
    private long id;
    private String title;
    private BigDecimal price;
}
