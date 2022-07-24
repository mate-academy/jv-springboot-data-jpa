package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.requestdto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.responsedto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(productRequestDto.getCategory());
        product.setPrice(productRequestDto.getPrice());
        return product;
    }

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory());
        return productResponseDto;
    }
}
