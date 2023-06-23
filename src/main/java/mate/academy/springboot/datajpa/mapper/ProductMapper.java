package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;

public class ProductMapper implements
        DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getCategoryById(dto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product entity) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(entity.getId());
        productResponseDto.setPrice(entity.getPrice());
        productResponseDto.setTitle(entity.getTitle());
        productResponseDto.setCategoryId(entity.getCategory().getId());
        return productResponseDto;
    }
}
