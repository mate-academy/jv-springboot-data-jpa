package mate.academy.springboot.datajpa.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements
        DtoMapper<RequestProductDto, ResponseProductDto, Product> {
    @Override
    public Product mapToModel(RequestProductDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());

        Category category = new Category();
        category.setId(dto.getCategoryid());
        product.setCategory(category);
        return product;
    }

    @Override
    public ResponseProductDto mapToDto(Product product) {
        ResponseProductDto dto = new ResponseProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }
}
