package mate.academy.springboot.service;

import java.util.List;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;

public interface ProductService {
    Product add(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    void update(Product product);

    List<Product> getAllBetweenPrice(Double from, Double to);

    List<Product> getAllByCategory(Category category);
}
