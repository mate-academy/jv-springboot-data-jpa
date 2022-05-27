package mate.academy.springboot.datajpa.service.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements RequestDtoMapper<Product, ProductRequestDto>,
        ResponseDtoMapper<Product, ProductResponseDto> {
    private final ModelMapper mapper;

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        return mapper.map(dto, Product.class);
    }

    @Override
    public ProductResponseDto mapToDto(Product model) {
        return mapper.map(model, ProductResponseDto.class);
    }
}
