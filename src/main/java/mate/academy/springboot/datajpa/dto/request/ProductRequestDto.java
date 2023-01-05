package mate.academy.springboot.datajpa.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private Integer price;
    private Long categoryId;
}
