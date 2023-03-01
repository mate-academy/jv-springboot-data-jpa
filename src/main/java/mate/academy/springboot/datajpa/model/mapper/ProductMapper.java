package mate.academy.springboot.datajpa.model.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(),
                                          product.getTitle(),
                                          product.getPrice(),
                                          product.getCategory());
    }

    public Product toModel(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setId(productResponseDto.getId());
        product.setTitle(productResponseDto.getTitle());
        product.setPrice(productResponseDto.getPrice());
        product.setCategory(productResponseDto.getCategory());
        return product;
    }
}
