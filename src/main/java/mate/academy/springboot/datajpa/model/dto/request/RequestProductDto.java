package mate.academy.springboot.datajpa.model.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
