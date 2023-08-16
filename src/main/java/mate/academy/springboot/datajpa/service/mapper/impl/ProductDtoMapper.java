package mate.academy.springboot.datajpa.service.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements
        DtoMapper<RequestProductDto, ResponseProductDto, Product> {
    @Override
    public Product mapToModel(RequestProductDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());

        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }

    @Override
    public ResponseProductDto mapToDto(Product model) {
        ResponseProductDto dto = new ResponseProductDto();
        dto.setTitle(model.getTitle());
        dto.setPrice(model.getPrice());
        dto.setCategory(model.getCategory().getName());
        return dto;
    }
}
