package mate.academy.springboot.datajpa.service.mappers;

import mate.academy.springboot.datajpa.models.Product;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {

    private final CategoryService categoryService;

    @Autowired
    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(categoryService.get(productRequestDto.getCategoryId()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseProductDto = new ProductResponseDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setTitle(product.getTitle());
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setCategory(product.getCategory().getId());
        return responseProductDto;
    }
}
