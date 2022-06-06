package mate.academy.springboot.datajpa.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long category_id;
}
