package mate.academy.springboot.datajpa.dto.mapper.impl;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements DtoRequestMapper<ProductRequestDto, Product> {
    @Override
    public Product fromDto(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);
        product.setTitle(dto.getTitle());

        return product;
    }
}
