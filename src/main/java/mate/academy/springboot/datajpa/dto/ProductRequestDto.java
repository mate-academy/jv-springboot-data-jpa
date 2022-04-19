package mate.academy.springboot.datajpa.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private int price;
    private String title;
    private Long categoryId;
}
