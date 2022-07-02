package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestProduct;
import mate.academy.springboot.datajpa.dto.response.ResponseProduct;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product fromDto(RequestProduct request) {
        Product product = new Product();
        product.setPrice(request.getPrice());
        product.setTitle(request.getTitle());
        product.setCategory(categoryService.getById(request.getCategoryId()));
        return product;
    }

    public ResponseProduct toDto(Product product) {
        ResponseProduct response = new ResponseProduct();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getCategory().getId());
        return response;
    }
}
