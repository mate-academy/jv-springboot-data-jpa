package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestDtoMapper {
    public Product toModel(ProductRequestDto data) {
        Product product = new Product();
        product.setTitle(data.getTitle());
        product.setPrice(data.getPrice());
        Category category = new Category();
        category.setName(data.getCategoryName());
        product.setCategory(category);
        return product;
    }
}
