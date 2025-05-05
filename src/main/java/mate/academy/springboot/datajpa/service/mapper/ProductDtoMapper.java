package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private final CategoryService categoryService;

    public ProductDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService
                .findById(productRequestDto.getCategoryId()));
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setTitle(product.getTitle());
        return responseDto;
    }
}
