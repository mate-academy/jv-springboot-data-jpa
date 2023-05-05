package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setCategory(product.getCategory());
        return productResponseDto;
    }

    public Product toProductModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }
}
