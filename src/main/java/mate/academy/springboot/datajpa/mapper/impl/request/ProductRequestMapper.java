package mate.academy.springboot.datajpa.mapper.impl.request;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRequestMapper implements DtoRequestMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product fromDto(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        return product;
    }
}
