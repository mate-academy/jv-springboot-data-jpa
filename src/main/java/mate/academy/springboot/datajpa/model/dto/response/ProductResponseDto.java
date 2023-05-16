package mate.academy.springboot.datajpa.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long productId;
    private String title;
    private int price;
    private Long categoryId;
}
