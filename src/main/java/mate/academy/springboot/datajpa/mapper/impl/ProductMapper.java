package mate.academy.springboot.datajpa.mapper.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.Mapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductRequestDto, ProductResponseDto> {
    @Override
    public Product toModel(ProductRequestDto dto) {
        return new Product(
                dto.getId(),
                dto.getTitle(),
                dto.getPrice(),
                new Category(dto.getCategory())
        );
    }

    @Override
    public ProductResponseDto toDto(Product model) {
        return new ProductResponseDto(
                model.getId(),
                model.getTitle(),
                model.getPrice(),
                model.getCategory().getName());
    }
}
