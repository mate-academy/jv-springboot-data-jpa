package mate.academy.springboot.datajpa.model.dto.mapper.impl.product;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.dto.request.product.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.product.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDtoMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product mapToModel(ProductRequestDto dto, Long id) {
        return Product.builder()
                .id(id)
                .title(dto.getTitle())
                .price(dto.getPrice())
                .category(categoryService.findById(dto.getCategoryId()))
                .build();
    }

    @Override
    public ProductResponseDto mapToDto(Product model) {
        return ProductResponseDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .price(model.getPrice())
                .categoryId(model.getCategory().getId())
                .build();
    }
}
