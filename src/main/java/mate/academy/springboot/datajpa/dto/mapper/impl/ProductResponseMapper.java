package mate.academy.springboot.datajpa.dto.mapper.impl;

import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements DtoResponseMapper<ProductResponseDto, Product> {
    @Override
    public ProductResponseDto toDto(Product object) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(object.getId());
        dto.setPrice(object.getPrice().doubleValue());
        dto.setTitle(object.getTitle());
        dto.setCategoryId(object.getCategory().getId());

        return dto;
    }
}
