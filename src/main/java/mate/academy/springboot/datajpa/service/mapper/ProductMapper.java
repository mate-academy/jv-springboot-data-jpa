package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
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
