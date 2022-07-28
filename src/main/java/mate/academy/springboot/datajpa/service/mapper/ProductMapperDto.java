package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponceDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperDto implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponceDtoMapper<ProductResponceDto, Product> {

    private final CategoryService categoryService;

    @Autowired
    public ProductMapperDto(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponceDto mapToDto(Product product) {
        ProductResponceDto productResponceDto = new ProductResponceDto();
        productResponceDto.setId(product.getId());
        productResponceDto.setTitle(product.getTitle());
        productResponceDto.setPrice(product.getPrice());
        productResponceDto.getCategoryId(product.getCategory().getId());
        return productResponceDto;
    }
}
