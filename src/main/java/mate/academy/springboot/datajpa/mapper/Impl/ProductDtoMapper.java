package mate.academy.springboot.datajpa.mapper.Impl;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements DtoMapper<Product,
        ProductRequestDto, ProductResponseDto> {
    @Override
    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setPrice(model.getPrice());
        responseDto.setCategory(model.getCategory());
        return responseDto;
    }
}
