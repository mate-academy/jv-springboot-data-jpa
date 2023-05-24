package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper extends DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    @Override
    public Product toModel(ProductRequestDto request) {
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        Category category = new Category();
        category.setId(request.getCategoryId());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(model.getId());
        productResponseDto.setTitle(model.getTitle());
        productResponseDto.setPrice(model.getPrice());
        productResponseDto.setCategoryId(model.getCategory().getId());
        return productResponseDto;
    }
}
