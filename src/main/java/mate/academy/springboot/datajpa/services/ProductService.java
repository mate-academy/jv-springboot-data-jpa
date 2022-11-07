package mate.academy.springboot.datajpa.services;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getByID(Long id);

    void deleteById(Long id);

    Product update(Product product);

    List<Product> getAllBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllWithCategories(List<Long> categoryId);
}
