package mate.academy.springboot.datajpa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private int price;
    private String title;
    private Long categoryId;
}
