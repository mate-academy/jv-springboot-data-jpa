package mate.academy.springboot.datajpa.service.mapper.impl;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequesdtDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements RequestDtoMapper<ProductRequesdtDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequesdtDto dto) {
        Product product = new Product();
        product.setCategory(dto.getCategory());
        return null;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        return null;
    }
}
