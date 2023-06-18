package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@ToString
@Getter
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
