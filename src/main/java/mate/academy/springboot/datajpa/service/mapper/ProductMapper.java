package mate.academy.springboot.datajpa.service.mapper;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.entity.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper implements DtoMapper<Product, ProductResponseDto, ProductRequestDto> {
    private final CategoryService categoryService;

    @Override
    public ProductResponseDto mapToDto(Product entity) {
        return new ProductResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getCategory().getName());
    }

    @Override
    public Product mapToEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.findByNameOrSave(dto.getCategory()));
        return product;
    }
}
