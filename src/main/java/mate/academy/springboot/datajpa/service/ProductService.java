package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {

    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllBetweenTwoValues(BigDecimal from, BigDecimal to);

    Map<Category, List<Product>> findAllInCategories(Map<String, String> params);
}
