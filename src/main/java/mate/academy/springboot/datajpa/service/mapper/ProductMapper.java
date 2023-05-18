package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.model.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper
        implements DtoMapper<Product, RequestProductDto, ResponseProductDto> {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product toModel(RequestProductDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
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
