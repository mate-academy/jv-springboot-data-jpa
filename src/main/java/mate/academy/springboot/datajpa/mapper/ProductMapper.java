package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.req.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.resp.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements ResponseDtoMapper<ProductResponseDto, Product>,
        RequestDtoMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    @Override
    public Product toModel(ProductRequestDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.getById(productDto.getCategoryId()));
        return product;
    }
}
