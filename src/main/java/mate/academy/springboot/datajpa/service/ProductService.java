package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product findById(Long id);

    Product update(Long id, Product product);

    void deleteById(Long id);

    List<Product> getAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> getAllByCategoryIds(Set<Long> categoryIds);
}
