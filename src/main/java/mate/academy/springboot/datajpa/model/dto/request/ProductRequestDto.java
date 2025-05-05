package mate.academy.springboot.datajpa.model.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequestDto {
    @NotNull
    private String title;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long categoryId;
}
