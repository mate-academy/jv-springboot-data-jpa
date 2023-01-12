package mate.academy.springboot.datajpa.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private float price;
    private Long categoryId;
}
