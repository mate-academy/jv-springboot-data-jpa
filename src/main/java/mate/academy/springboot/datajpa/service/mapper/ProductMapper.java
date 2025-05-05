package mate.academy.springboot.datajpa.service.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.get(dto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryId(product.getCategory().getId());
        return responseDto;
    }
}
