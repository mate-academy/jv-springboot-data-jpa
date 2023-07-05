package mate.academy.springboot.datajpa.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String title;
    private Float price;
    private String category;
}
