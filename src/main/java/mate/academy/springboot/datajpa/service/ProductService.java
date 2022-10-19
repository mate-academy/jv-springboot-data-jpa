package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllBuThePriceBetween(Double from, Double to);

    List<Product> getAllByTheCategoriesIn(Map<String, String> params);
}
