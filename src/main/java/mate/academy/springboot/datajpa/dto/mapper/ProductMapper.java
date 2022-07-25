package mate.academy.springboot.datajpa.dto.mapper;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.dto.RequestProduct;
import mate.academy.springboot.datajpa.dto.ResponseProduct;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Product toModel(RequestProduct req) {
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setPrice(BigDecimal.valueOf(req.getPrice()));
        product.setCategory(categoryService.get(req.getCategoryId()));
        return product;
    }

    public ResponseProduct toResponseProduct(Product product) {
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setId(product.getId());
        responseProduct.setTitle(product.getTitle());
        responseProduct.setPrice(product.getPrice().longValue());
        responseProduct.setCategory(product.getCategory().getName());
        return responseProduct;
    }
}
