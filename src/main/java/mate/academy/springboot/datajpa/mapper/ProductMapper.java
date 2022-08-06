package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setCategory(categoryService.findBy(dto.getCategoryId()));
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        return product;
    }

    public Product toModel(ProductResponseDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setCategory(categoryService.findBy(dto.getCategoryId()));
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        return product;
    }

    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setCategoryId(product.getCategory().getId());
        dto.setPrice(product.getPrice());
        dto.setTitle(product.getTitle());
        return dto;
    }
}
