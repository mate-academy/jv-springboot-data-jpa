package mate.academy.springboot.datajpa.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long category;
}
