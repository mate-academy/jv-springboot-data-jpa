package mate.academy.springboot.datajpa.service.mapper.request;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
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
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        return product;
    }
}
