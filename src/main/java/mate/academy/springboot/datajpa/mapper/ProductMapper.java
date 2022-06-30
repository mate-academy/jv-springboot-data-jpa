package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.resquest.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
                    ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }
}
