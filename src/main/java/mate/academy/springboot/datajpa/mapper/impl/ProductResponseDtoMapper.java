package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseDtoMapper implements ResponseDtoMapper<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setPrice(model.getPrice());
        dto.setCategoryId(model.getCategory().getId());
        return dto;
    }
}
