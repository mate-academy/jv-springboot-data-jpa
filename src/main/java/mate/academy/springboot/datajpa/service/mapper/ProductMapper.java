package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.config.MapperConfig;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ProductMapper extends DtoMapper<ProductResponseDto, Product, ProductRequestDto> {
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponseDto toDto(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequestDto productRequestDto);
}
