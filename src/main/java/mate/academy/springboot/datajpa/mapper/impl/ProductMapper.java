package mate.academy.springboot.datajpa.mapper.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductMapper implements RequestDtoMapper<Product, ProductRequestDto>,
                                      ResponseDtoMapper<Product, ProductResponseDto> {
    private final CategoryService categoryService;

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(product.getTitle());
        product.setPrice(product.getPrice());
        product.setCategory(categoryService.findById(productRequestDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setCategoryId(product.getCategory().getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        return productResponseDto;
    }
}
