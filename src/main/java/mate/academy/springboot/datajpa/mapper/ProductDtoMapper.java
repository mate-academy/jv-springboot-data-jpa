package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements ResponseDtoMapper<Product, ProductResponseDto>,
        RequestDtoMapper<ProductRequestDto, Product> {
    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory());
        return productResponseDto;
    }

    @Override
    public Product mapToEntity(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(productRequestDto.getCategory());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }
}
