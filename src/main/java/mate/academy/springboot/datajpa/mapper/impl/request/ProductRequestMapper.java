package mate.academy.springboot.datajpa.mapper.impl.request;

import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements DtoRequestMapper<ProductRequestDto, Product> {
    private CategoryService categoryService;

    public ProductRequestMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product fromDto(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return product;
    }
}
