package mate.academy.springboot.datajpa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private Long categoryId;
}
