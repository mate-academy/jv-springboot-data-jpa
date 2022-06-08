package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.RequestProductDto;
import mate.academy.springboot.datajpa.dto.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(RequestProductDto requestProductDto) {
        Product product = new Product();
        product.setTitle(requestProductDto.getTitle());
        product.setPrice(requestProductDto.getPrice());
        product.setCategory(categoryService.getById(requestProductDto.getCategoryId()));
        return product;
    }

    public ResponseProductDto toDto(Product product) {
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setTitle(product.getTitle());
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setCategory_id(product.getCategory().getId());
        return responseProductDto;
    }
}
