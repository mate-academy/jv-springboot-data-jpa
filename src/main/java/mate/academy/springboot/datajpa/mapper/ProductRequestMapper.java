package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {
    private CategoryService categoryService;

    public ProductRequestMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(ProductRequestDto model) {
        Product product = new Product();
        product.setTitle(model.getTitle());
        product.setPrice(model.getPrice());
        product.setCategory(categoryService.getById(model.getCategoryId()));
        return product;
    }
}
