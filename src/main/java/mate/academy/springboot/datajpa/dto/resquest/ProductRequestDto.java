package mate.academy.springboot.datajpa.dto.resquest;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;

public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category category) {
        this.categoryId = categoryId;
    }
}
