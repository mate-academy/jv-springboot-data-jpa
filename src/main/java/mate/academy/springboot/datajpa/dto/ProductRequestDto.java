package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
