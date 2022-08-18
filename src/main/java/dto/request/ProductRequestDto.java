package dto.request;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String productTitle;
    private BigDecimal productPrice;
    private Long categoryId;
}
