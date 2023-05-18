package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    boolean delete(Product product);

    Product update(Product product);

    List<Product> getByPriceBetween(Long from, Long to);

    List<Product> getByCategory(Category category);
}
