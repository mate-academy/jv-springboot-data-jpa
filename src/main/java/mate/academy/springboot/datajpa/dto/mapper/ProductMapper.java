package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.requestdto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.responsedto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<Product, ProductRequestDto>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(productRequestDto.getCategory());
        product.setTitle(product.getTitle());
        product.setPrice(product.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        return productResponseDto;
    }
}
