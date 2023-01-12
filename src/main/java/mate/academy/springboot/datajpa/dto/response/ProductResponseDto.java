package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;

@Data
public class ProductResponseDto {
    private long id;
    private String title;
    private float price;
    private Long categoryId;

}
