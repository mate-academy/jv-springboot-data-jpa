package mate.academy.springboot.datajpa.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
