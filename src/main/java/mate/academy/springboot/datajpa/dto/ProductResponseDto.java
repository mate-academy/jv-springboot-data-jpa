package mate.academy.springboot.datajpa.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private int price;
    private String title;
    private Long categoryId;
}
