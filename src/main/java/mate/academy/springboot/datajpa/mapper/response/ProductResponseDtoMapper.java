package mate.academy.springboot.datajpa.mapper.response;

import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.lib.Mapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Product;

@Mapper
public class ProductResponseDtoMapper
        implements ResponseDtoMapper<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(entity.getId());
        productDto.setTitle(entity.getTitle());
        productDto.setPrice(entity.getPrice());
        productDto.setCategoryName(entity.getCategory().getName());
        return productDto;
    }
}
