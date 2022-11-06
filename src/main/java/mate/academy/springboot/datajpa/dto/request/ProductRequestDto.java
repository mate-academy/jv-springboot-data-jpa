package mate.academy.springboot.datajpa.dto.request;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;

public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
