package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.domain.Category;
import mate.academy.springboot.datajpa.domain.Product;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public ProductMapper(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setCategory(categoryMapper.toDto(product.getCategory()));
        dto.setPrice(product.getPrice());
        return dto;
    }

    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        Category category = categoryService.findByName(dto.getCategory().getName());
        product.setCategory(category);
        product.setTitle(dto.getTitle());
        return product;
    }
}
