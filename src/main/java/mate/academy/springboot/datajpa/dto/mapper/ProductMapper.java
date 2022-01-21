package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .title(product.getTitle())
                .category(product.getCategory())
                .build();
    }

    public Product toModel(ProductRequestDto requestDto) {
        return Product.builder()
                .price(requestDto.getPrice())
                .title(requestDto.getTitle())
                .category(requestDto.getCategory())
                .build();
    }
}
