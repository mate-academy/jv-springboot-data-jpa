package mate.academy.springboot.datajpa.dto.response;

import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}