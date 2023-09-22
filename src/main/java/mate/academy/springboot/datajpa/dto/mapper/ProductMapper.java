package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toRespDto(Product prod) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(prod.getId());
        dto.setTitle(prod.getTitle());
        dto.setPrice(prod.getPrice());
        dto.setCategoryId(prod.getCategory().getId());
        return dto;
    }

    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        return product;
    }
}
