package mate.academy.springboot.service.mapper;

import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.model.dto.request.ProductRequestDto;
import mate.academy.springboot.model.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product dtoToModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setCategory(new Category(requestDto.getCategory()));
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        return product;
    }

    public ProductResponseDto modelToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setCategory(product.getCategory().getName());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        return responseDto;
    }
}