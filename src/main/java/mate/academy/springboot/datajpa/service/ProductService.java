package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends GenericService<Product> {
    @Override
    Product add(Product product);

    @Override
    Product update(Product product);

    List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findProductsByCategoryIdIn(List<Long> categoriesIds);
}
