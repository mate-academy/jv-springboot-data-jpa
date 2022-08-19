package mate.academy.springboot.datajpa.dto.product;

import java.math.BigDecimal;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductRequestDto that = (ProductRequestDto) o;
        return Objects.equals(title, that.title)
                && Objects.equals(price, that.price)
                && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, categoryId);
    }
}
