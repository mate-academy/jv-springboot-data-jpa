package mate.academy.springboot.datajpa.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.exception.CategoryNotFoundException;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toProduct(ProductRequestDto requestDto) throws CategoryNotFoundException {
        Product product = new Product();
        product.setTitle(requestDto.getTittle());
        product.setPrice(requestDto.getPrice());
        if (requestDto.getCategoryId() != null) {
            product.setCategory(categoryService.findById(requestDto.getCategoryId()));
        }
        return product;
    }

    public List<ProductResponseDto> toProductResponseDtos(List<Product> products) {
        return products
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTittle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryId(product.getCategory().getId());
        return responseDto;
    }
}
