package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestDtoMapper implements RequestDtoMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    public ProductRequestDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto dto) {
        Product model = new Product();
        model.setTitle(dto.getTitle());
        model.setPrice(dto.getPrice());
        model.setCategory(categoryService.getById(dto.getCategoryId()));
        return model;
    }
}
