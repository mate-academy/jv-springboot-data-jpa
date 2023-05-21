package mate.academy.springboot.datajpa.service.mapper;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.respons.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        product.setPrice(dto.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setCategoryId(product.getCategory().getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        return productResponseDto;
    }
}
