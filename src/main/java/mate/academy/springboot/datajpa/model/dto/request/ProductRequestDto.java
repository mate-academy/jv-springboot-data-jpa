package mate.academy.springboot.datajpa.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ProductRequestDto {
    @Positive
    private Long id;
    @NotBlank
    private String title;
    @Positive
    private BigDecimal price;
    @NotBlank
    private String category;
}
