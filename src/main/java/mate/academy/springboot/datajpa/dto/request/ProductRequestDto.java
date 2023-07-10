package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;

public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
