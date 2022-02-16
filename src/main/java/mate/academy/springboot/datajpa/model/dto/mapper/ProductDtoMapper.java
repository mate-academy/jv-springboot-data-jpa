package mate.academy.springboot.datajpa.model.dto.mapper;


import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private final CategoryService categoryService;

    public ProductDtoMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setCategoryId(product.getCategory().getId());
        responseDto.setPrice(product.getPrice());
        responseDto.setTitle(product.getTitle());
        return responseDto;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }
}
