package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDto {

    private String title;

    private Integer price;

    private String categoryName;

}
