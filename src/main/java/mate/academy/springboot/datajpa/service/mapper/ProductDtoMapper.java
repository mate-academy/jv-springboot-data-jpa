package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper
        implements DtoMapper<ProductRequestDto, Product, ProductResponseDto> {
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
    public ProductResponseDto mapToDto(Product model) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setCategoryId(model.getCategory().getId());
        productResponseDto.setId(model.getId());
        productResponseDto.setTitle(model.getTitle());
        productResponseDto.setPrice(model.getPrice());
        return productResponseDto;
    }
}
