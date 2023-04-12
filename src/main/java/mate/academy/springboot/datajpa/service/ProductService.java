package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
    void delete(Long id);
    Product update(Product product);
    List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);
    List<Product> findAllByCategoryIds(List<Long> ids);
}
