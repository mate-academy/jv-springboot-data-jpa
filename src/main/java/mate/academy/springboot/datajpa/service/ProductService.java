package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(long id);

    Product update(Product product);

    List<Product> getProductsByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> getProductsByCategoryIn(List<Long> categoriesId);
}
