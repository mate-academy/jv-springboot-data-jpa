package mate.academy.springboot.datajpa.mapper;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setPrice(product.getPrice());
        dto.setTitle(product.getTitle());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryRepository.getById(productRequestDto.getCategoryId()));
        product.setTitle(productRequestDto.getTitle());
        return product;
    }
}
