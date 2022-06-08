package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
public class RequestProductDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
