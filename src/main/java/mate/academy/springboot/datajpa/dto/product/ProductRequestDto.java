package mate.academy.springboot.datajpa.dto.product;

import lombok.Data;

import java.math.BigDecimal;

public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long categoryId;

    public ProductRequestDto(String title, BigDecimal price, Long categoryId) {
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
    }

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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
