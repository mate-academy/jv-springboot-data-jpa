package mate.academy.springboot.datajpa.service.mapper.request;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestDtoMapper implements RequestDtoMapper<ProductRequestDto, Product> {
    private CategoryService categoryService;

    public ProductRequestDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setCategory(categoryService.get(dto.getCategoryId()));
        return product;
    }
}
