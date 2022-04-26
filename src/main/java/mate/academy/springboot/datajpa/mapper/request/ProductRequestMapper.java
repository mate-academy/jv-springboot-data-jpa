package mate.academy.springboot.datajpa.mapper.request;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRequestMapper implements DtoRequestMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    @Override
    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        Category category = categoryService.findById(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
