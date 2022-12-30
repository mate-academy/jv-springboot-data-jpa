package mate.academy.springboot.model.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private Double price;
    private String category;
}
