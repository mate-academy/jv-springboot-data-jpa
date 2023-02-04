package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private Long price;
    private Category category;
}
