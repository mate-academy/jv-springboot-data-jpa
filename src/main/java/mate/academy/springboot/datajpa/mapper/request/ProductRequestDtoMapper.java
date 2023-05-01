package mate.academy.springboot.datajpa.mapper.request;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.lib.Mapper;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class ProductRequestDtoMapper
        implements RequestDtoMapper<Product, ProductRequestDto> {
    private final CategoryService categoryService;

    @Autowired
    public ProductRequestDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
        return product;
    }
}
