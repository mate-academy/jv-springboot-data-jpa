package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;

public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private CategoryRequestDto category;

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

    public CategoryRequestDto getCategory() {
        return category;
    }

    public void setCategory(CategoryRequestDto category) {
        this.category = category;
    }
}
