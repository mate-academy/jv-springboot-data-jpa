package mate.academy.springboot.datajpa.dto.product;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private int price;
    private Long categoryId;
}
