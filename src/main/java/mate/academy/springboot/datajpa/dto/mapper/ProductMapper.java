package mate.academy.springboot.datajpa.dto.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements DtoRequestMapper<ProductRequestDto, Product>,
        DtoResponseMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product toModel(ProductRequestDto dto) {
        Product model = new Product();
        model.setTitle(dto.getTitle());
        model.setPrice(dto.getPrice());
        model.setCategory(categoryService.get(dto.getCategoryId()));
        return model;
    }

    @Override
    public ProductResponseDto toDto(Product dto) {
        ProductResponseDto model = new ProductResponseDto();
        model.setTitle(dto.getTitle());
        model.setPrice(dto.getPrice());
        model.setId(dto.getId());
        model.setCategoryId(dto.getCategory().getId());
        return model;
    }
}
