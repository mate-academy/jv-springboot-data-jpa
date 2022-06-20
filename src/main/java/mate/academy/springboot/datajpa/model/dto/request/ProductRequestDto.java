package mate.academy.springboot.datajpa.model.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private Double price;
    private Long categoryId;
}
