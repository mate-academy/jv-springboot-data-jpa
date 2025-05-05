package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RequestProductDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
