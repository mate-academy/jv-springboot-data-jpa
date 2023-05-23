package mate.academy.springboot.datajpa.model.dto.request;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
