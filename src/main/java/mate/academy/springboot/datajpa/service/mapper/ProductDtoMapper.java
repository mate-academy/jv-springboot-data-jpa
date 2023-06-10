package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class ProductDtoMapper implements DtoMapper<Product,
        ProductResponseDto,
        ProductRequestDto> {
    @Autowired
    protected CategoryService categoryService;

    @Override
    @Mapping(target = "categoryId", source = "entity.category.id")
    public abstract ProductResponseDto toDto(Product entity);

    @Override
    @Mapping(target = "category", expression = "java(categoryService.find(dto.getCategoryId()))")
    public abstract Product toModel(ProductRequestDto dto);
}
