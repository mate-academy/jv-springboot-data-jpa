package mate.academy.springboot.datajpa.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String category;
}
