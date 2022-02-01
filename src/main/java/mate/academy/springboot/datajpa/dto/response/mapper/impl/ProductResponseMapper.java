package mate.academy.springboot.datajpa.dto.response.mapper.impl;

import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.response.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements ResponseMapper<ProductResponseDto, Product> {
    @Override
    public ProductResponseDto mapToDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setPrice(model.getPrice());
        responseDto.setTitle(model.getTitle());
        responseDto.setCategoryName(model.getCategory().getName());
        return responseDto;
    }
}
