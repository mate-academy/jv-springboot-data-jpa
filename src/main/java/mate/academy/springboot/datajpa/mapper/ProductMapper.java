package mate.academy.springboot.datajpa.mapper;

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

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoryId(product.getCategory().getId());
        return productResponseDto;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        product.setId(productRequestDto.getId());
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        return product;
    }
}
