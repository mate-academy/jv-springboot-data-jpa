package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        DtoMapper<ProductRequestDto, ProductResponseDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.getById(requestDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setPrice(model.getPrice());
        dto.setCategoryName(model.getCategory().getName());
        return dto;
    }
}
