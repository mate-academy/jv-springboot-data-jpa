package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.model.dto.response.ResponseProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper
        implements DtoMapper<Product, RequestProductDto, ResponseProductDto> {

    @Override
    public Product toModel(RequestProductDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        Category category = new Category();
        category.setId(requestDto.getCategoryId());
        product.setCategory(category);
        return product;
    }

    @Override
    public ResponseProductDto toDto(Product element) {
        ResponseProductDto responseDto = new ResponseProductDto();
        responseDto.setId(element.getId());
        responseDto.setTitle(element.getTitle());
        responseDto.setPrice(element.getPrice());
        responseDto.setCategoryId(element.getCategory().getId());
        return responseDto;
    }
}
