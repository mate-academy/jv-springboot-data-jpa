package mate.academy.springboot.service.mapper;

import mate.academy.springboot.dto.request.ProductUpdateRequestDto;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.dto.request.ProductRequestDto;
import mate.academy.springboot.dto.response.ProductResponseDto;
import mate.academy.springboot.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<Product, ProductRequestDto>,
        ResponseDtoMapper<Product, ProductResponseDto>,
        UpdateRequestDtoMapper<Product, ProductUpdateRequestDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoryId(product.getCategory().getId());
        return productResponseDto;
    }

    @Override
    public Product toModelUpdate(ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = new Product();
        product.setId(productUpdateRequestDto.getId());
        product.setTitle(productUpdateRequestDto.getTitle());
        product.setPrice(productUpdateRequestDto.getPrice());
        product.setCategory(categoryService.getById(productUpdateRequestDto.getCategoryId()));
        return product;
    }
}
