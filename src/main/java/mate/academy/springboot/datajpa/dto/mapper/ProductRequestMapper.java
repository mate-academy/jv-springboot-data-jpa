package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements RequestDtoMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    public ProductRequestMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.get(dto.getCategoryId()));
        return product;
    }
}
