package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements
        DtoMapper<ProductResponseDto, Product, ProductRequestDto> {
    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setPrice(model.getPrice());
        responseDto.setTitle(model.getTitle());
        responseDto.setCategoryId(model.getCategory().getId());
        return responseDto;
    }

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setPrice(requestDto.getPrice());
        product.setTitle(requestDto.getTitle());
        Category category = new Category();
        category.setId(requestDto.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
