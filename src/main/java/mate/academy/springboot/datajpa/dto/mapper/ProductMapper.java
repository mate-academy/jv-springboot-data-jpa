package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        GenericMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        return new Product()
                .setTitle(productRequestDto.getTitle())
                .setPrice(productRequestDto.getPrice())
                .setCategory(categoryService.get(productRequestDto.getCategoryId()));
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        return new ProductResponseDto()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setPrice(product.getPrice())
                .setCategoryId(product.getCategory().getId());
    }
}
