package mate.academy.springboot.datajpa.dto.req;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
