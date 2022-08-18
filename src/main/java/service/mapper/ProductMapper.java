package service.mapper;

import dto.request.ProductRequestDto;
import dto.response.ProductResponseDto;
import model.Category;
import model.Product;
import org.springframework.stereotype.Component;
import service.CategoryService;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        Category category = categoryService.getById(dto.getCategoryId());
        product.setPrice(dto.getProductPrice());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(product.getId());
        responseDto.setCategoryId(product.getCategory().getId());
        responseDto.setProductPrice(product.getPrice());
        responseDto.setProductTitle(product.getTitle());
        return responseDto;
    }
}
