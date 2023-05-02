package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product entity) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(entity.getId());
        productResponseDto.setTitle(entity.getTitle());
        productResponseDto.setPrice(entity.getPrice());
        productResponseDto.setCategory(entity.getCategory());
        return productResponseDto;
    }
}
