package mate.academy.springboot.datajpa.model.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductRequestDto fromModelToRequestDto(Product product) {
        return new ProductRequestDto(product.getTitle(), product.getPrice(), product.getCategory());
    }

    public ProductResponseDto fromModelToResponseDto(Product product) {
        return new ProductResponseDto(product.getId(),
                                          product.getTitle(),
                                          product.getPrice(),
                                          product.getCategory());
    }

    public Product fromResponseDtoToModel(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setId(productResponseDto.getId());
        product.setTitle(productResponseDto.getTitle());
        product.setPrice(productResponseDto.getPrice());
        product.setCategory(productResponseDto.getCategory());
        return product;
    }
}
