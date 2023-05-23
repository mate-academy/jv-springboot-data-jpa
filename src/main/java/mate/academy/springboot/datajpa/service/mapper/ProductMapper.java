package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<Product, RequestProductDto, ResponseProductDto> {
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
    public ResponseProductDto toDto(Product product) {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setTitle(product.getTitle());
        responseProductDto.setCategoryId(product.getCategory().getId());
        return responseProductDto;
    }
}
