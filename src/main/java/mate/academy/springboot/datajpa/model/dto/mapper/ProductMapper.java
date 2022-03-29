package mate.academy.springboot.datajpa.model.dto.mapper;

import java.util.Objects;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());

        Category categoryFromProduct = product.getCategory();
        Category category = new Category();

        if (Objects.nonNull(categoryFromProduct)) {
            category.setId(product.getCategory().getId());
            category.setName(product.getCategory().getName());
        }

        productResponseDto.setCategory(category);
        productResponseDto.setTitle(product.getTitle());

        return productResponseDto;
    }

    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());

        return product;
    }
}
