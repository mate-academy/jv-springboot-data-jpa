package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final CategoryService categoryService;

    @Autowired
    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.getCategoryById(requestDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto toResponseDto(Product entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setTitle(entity.getTitle());
        responseDto.setPrice(entity.getPrice());
        responseDto.setCategoryId(entity.getCategory().getId());
        return responseDto;
    }
}
