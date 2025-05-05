package dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long productId;
    private String productTitle;
    private BigDecimal productPrice;
    private Long categoryId;
}
