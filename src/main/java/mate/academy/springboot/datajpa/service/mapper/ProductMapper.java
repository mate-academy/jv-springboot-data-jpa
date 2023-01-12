package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(ProductRequestDto dto) {
        return null;
    }

    public ProductResponseDto toDto(Product product) {
        return null;
    }
}
