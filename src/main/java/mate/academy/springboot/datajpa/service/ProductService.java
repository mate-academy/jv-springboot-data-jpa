package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Optional<Product> get(Long productId);

    void deleteById(Long productId);

    void update(Product product);

    List<Product> findAllByPriceBetween(int from, int to);

    List<Category> findAllInCategories(List<Long> ids);
}
