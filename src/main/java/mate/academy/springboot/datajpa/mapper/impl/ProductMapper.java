package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<ProductRequestDto, ProductResponseDto, Product> {
    @Override
    public Product mapToEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setCategory(new Category(dto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product entity) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(entity.getId());
        productResponseDto.setTitle(entity.getTitle());
        productResponseDto.setPrice(entity.getPrice());
        productResponseDto.setCategoriesId(entity.getCategory().getId());
        return productResponseDto;
    }
}
