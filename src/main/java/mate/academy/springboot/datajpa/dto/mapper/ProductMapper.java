package mate.academy.springboot.datajpa.dto.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements DtoRequestMapper<ProductRequestDto, Product>,
        DtoResponseMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product fromDto(ProductRequestDto object) {
        Product product = new Product();
        product.setTitle(object.getTitle());
        product.setPrice(object.getPrice());
        product.setCategory(categoryService.get(object.getCategoryId()));
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
