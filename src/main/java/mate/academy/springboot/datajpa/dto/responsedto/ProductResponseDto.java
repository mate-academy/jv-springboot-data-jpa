package mate.academy.springboot.datajpa.dto.responsedto;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private Double price;
    private Category category;
}
