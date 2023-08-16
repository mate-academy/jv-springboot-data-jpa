package mate.academy.springboot.datajpa.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ResponseProductDto {
    private String title;
    private BigDecimal price;
    private String category;
}
