package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category category;
}
