package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final CategoryService categoryService;

    @Autowired
    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.findById(dto.getCategoryId()));
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
