package mate.academy.springboot.datajpa.dto.requestdto;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductRequestDto {
    private String title;
    private Double price;
    private Category category;
}
