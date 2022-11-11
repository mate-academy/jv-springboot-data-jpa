package mate.academy.springboot.datajpa.services.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoryId(product.getCategory().getId());
        return productResponseDto;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        return product;
    }
}
