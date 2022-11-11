package mate.academy.springboot.datajpa.services;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllWithCategories(List<Long> categoryId);
}
