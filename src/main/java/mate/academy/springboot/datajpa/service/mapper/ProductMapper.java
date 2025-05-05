package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements ResponseDtoMapper<ProductResponseDto, Product>,
        RequestDtoMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        return product;
    }
}
