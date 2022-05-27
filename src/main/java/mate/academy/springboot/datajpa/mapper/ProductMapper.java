package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductRequestDto, ProductResponseDto> {
    @Override
    public ProductResponseDto mapToDto(Product product) {
        return new ProductResponseDto()
            .setTitle(product.getTitle())
            .setPrice(product.getPrice())
            .setCategoryName(product.getCategory().getName());
    }

    @Override
    public Product mapToEntity(ProductRequestDto dto) {
        return new Product()
            .setTitle(dto.getTitle())
            .setPrice(dto.getPrice());
    }
}
