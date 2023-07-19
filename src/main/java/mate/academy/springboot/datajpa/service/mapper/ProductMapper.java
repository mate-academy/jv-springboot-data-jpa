package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<Product, ProductResponseDto, ProductRequestDto> {
    @Override
    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setCategory(entity.getCategory());
        responseDto.setTitle(entity.getTitle());
        responseDto.setPrice(entity.getPrice());
        return responseDto;
    }

    @Override
    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        return product;
    }
}
