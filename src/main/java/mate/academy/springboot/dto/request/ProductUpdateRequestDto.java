package mate.academy.springboot.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequestDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
