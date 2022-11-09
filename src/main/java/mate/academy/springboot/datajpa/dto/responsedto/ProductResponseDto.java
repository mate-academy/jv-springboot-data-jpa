package mate.academy.springboot.datajpa.dto.responsedto;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

@Data
public class ProductResponseDto {
    private long id;
    private String title;
    private double price;
    private Category category;
}
