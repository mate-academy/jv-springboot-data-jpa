package mate.academy.springboot.datajpa.mapper.impl.request;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoRequestMapper implements DtoRequestMapper<ProductRequestDto, Product> {
    @Override
    public Product fromDto(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
