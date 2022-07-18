package mate.academy.springboot.datajpa.service.mapper;

import lombok.Data;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductRequestMapper implements RequestDtoMapper<ProductRequestDto, Product> {
    private final CategoryService categoryService;

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return product;
    }
}
