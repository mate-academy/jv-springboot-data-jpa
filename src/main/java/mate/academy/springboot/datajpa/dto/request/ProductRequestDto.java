package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String title;
    private Integer price;
    private Long categoryId;
}
