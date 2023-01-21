package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private final CategoryDtoMapper categoryDtoMapper;
    private final CategoryService categoryService;

    public ProductDtoMapper(CategoryDtoMapper categoryDtoMapper, CategoryService categoryService) {
        this.categoryDtoMapper = categoryDtoMapper;
        this.categoryService = categoryService;
    }

    public Product requestToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.get(dto.getCategoryId()));
        return product;
    }

    public ProductResponseDto modelToResponse(Product model) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setPrice(model.getPrice());
        dto.setCategoryId(model.getCategory().getId());
        return dto;
    }
}
