package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private int price;
    private String title;
    private Long categoryId;
}
