package mate.academy.springboot.datajpa.dto.response;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
