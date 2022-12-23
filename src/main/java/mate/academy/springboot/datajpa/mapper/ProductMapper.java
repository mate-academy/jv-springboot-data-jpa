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

    public ProductResponseDto modelToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setPrice(product.getPrice());
        responseDto.setTitle(product.getTitle());
        responseDto.setCategoryId(product.getCategory().getId());
        return responseDto;
    }

    public Product dtoToModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setPrice(requestDto.getPrice());
        product.setTitle(requestDto.getTitle());
        product.setCategory(categoryRepository.getById(requestDto.getCategoryId()));
        return product;
    }
}
