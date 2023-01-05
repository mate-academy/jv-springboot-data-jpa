package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> findAllByCategoryIdIn(Collection<Long> categoryIds);
}
