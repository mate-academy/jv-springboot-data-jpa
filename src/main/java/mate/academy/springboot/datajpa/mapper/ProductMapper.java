package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequest;
import mate.academy.springboot.datajpa.dto.ProductResponse;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductRequest, ProductResponse> {

    @Override
    public ProductResponse mapToDto(Product product) {
        return new ProductResponse()
            .setTitle(product.getTitle())
            .setPrice(product.getPrice())
            .setCategoryName(product.getCategory().getName());
    }

    @Override
    public Product mapToEntity(ProductRequest dto) {
        return new Product()
            .setTitle(dto.getTitle())
            .setPrice(dto.getPrice());
    }
}
