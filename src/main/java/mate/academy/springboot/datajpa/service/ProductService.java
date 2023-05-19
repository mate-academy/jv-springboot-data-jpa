package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    void delete(Product product);

    Product update(Product product);

    List<Product> findProductsByPriceBetween(Long from, Long to);

    List<Product> findProductsByCategoryName(List<String> categories);
}
