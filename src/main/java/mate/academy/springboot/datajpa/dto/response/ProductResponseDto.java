package mate.academy.springboot.datajpa.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
