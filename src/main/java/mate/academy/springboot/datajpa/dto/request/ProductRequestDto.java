package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private double price;
    private Long categoryId;
}
