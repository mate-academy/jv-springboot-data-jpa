package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;

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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{"
                + "title='" + title + '\''
                + ", price=" + price
                + ", categoryId=" + categoryId + '}';
    }
}
