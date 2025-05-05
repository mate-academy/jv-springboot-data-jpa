package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper extends DtoMapper<Product,
        ProductResponseDto,
        ProductRequestDto> {
    @Override
    ProductResponseDto toDto(Product entity);

    @Override
    Product toModel(ProductRequestDto dto);
}
