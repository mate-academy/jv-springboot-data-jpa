package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private Long categoryId;
}
