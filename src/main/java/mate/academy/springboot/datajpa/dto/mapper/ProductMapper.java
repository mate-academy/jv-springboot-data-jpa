package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        ModelMapper<ProductResponseDto, Product, ProductRequestDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setCategoryId(product.getCategory().getId());
        dto.setPrice(product.getPrice());
        dto.setTitle(product.getTitle());
        return dto;
    }

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product model = new Product();
        model.setCategory(categoryService.getById(requestDto.getCategoryId()));
        model.setPrice(requestDto.getPrice());
        model.setTitle(requestDto.getTitle());
        return model;
    }
}
