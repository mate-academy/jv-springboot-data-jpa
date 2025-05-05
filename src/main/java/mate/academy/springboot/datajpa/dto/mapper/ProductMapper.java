package mate.academy.springboot.datajpa.dto.mapper;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(ProductRequestDto req) {
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setPrice(BigDecimal.valueOf(req.getPrice()));
        product.setCategory(categoryService.get(req.getCategoryId()));
        return product;
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto responseProduct = new ProductResponseDto();
        responseProduct.setId(product.getId());
        responseProduct.setTitle(product.getTitle());
        responseProduct.setPrice(product.getPrice().longValue());
        responseProduct.setCategory(product.getCategory().getName());
        return responseProduct;
    }
}
