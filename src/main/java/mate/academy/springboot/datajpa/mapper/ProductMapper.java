package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<RequestProductDto, Product>,
        ResponseDtoMapper<ResponseProductDto, Product> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(RequestProductDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setCategory(categoryService.getById(dto.getCategoryId()));
        return product;
    }

    @Override
    public ResponseProductDto mapToDto(Product product) {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setTitle(product.getTitle());
        responseProductDto.setCategoryId(product.getCategory().getId());
        return responseProductDto;
    }
}
