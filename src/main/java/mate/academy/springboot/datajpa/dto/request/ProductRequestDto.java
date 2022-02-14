package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import mate.academy.springboot.datajpa.model.Category;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
public class ProductRequestDto {
    @Size(max = 20)
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    @UniqueElements
    private Category categoryId;
}
