package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setCategory(categoryMapper.toDto(product.getCategory()));
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setId(product.getId());
        return productResponseDto;
    }
}
