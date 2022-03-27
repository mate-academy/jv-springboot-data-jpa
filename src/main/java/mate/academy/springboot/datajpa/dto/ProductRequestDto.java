package mate.academy.springboot.datajpa.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private String price;
    private Long categoryId;
}
