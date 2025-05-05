package mate.academy.springboot.datajpa.dto.mapper.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDtoMapper implements
        DtoMapper<ProductRequestDto, Product, ProductResponseDto> {

    @Override
    public Product mapToModel(ProductRequestDto requestDto) {
        Category category = new Category();
        category.setId(requestDto.getCategoryId());
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryId(product.getCategory().getId());
        return responseDto;
    }
}
