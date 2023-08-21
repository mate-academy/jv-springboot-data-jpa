package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }
}
