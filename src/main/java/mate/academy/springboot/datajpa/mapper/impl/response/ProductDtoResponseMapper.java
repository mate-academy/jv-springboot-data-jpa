package mate.academy.springboot.datajpa.mapper.impl.response;

import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoResponseMapper implements DtoResponseMapper<ProductResponseDto, Product> {
    @Override
    public ProductResponseDto toDto(Product object) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(object.getId());
        productResponseDto.setTitle(object.getTitle());
        productResponseDto.setPrice(object.getPrice());
        productResponseDto.setCategoryId(object.getCategory().getId());
        return productResponseDto;
    }
}
