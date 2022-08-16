package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductMapper(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setPrice(product.getPrice());
        responseDto.setTitle(product.getTitle());
        responseDto.setCategoryId(product.getCategory().getId());
        return responseDto;
    }

    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        return product;
    }
}
