package mate.academy.springboot.datajpa.dto;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private final CategoryService categoryService;

    public ProductDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(new BigDecimal(productDto.getPrice()));
        product.setCategory(categoryService.getById(productDto.getCategoryId()));
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice().toString());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
}
