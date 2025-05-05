package mate.academy.springboot.datajpa.model.dto.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements RequestMapper<ProductRequestDto, Product>,
        ResponseMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.findById(dto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory().getName());
        return dto;
    }
}
