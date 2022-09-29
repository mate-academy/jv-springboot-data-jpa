package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoRequestMapper<ProductRequestDto, Product>,
        DtoResponseMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product fromDto(ProductRequestDto object) {
        Product product = new Product();
        product.setTitle(object.getTitle());
        product.setPrice(object.getPrice());
        product.setCategory(categoryService.get(object.getCategoryId()).orElseThrow());
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product object) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setTitle(object.getTitle());
        responseDto.setPrice(object.getPrice());
        responseDto.setId(object.getId());
        responseDto.setCategoryId(object.getCategory().getId());
        return responseDto;
    }
}
