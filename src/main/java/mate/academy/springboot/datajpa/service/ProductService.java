package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.entity.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAllWherePriceBetween(double from, double to);

    List<Product> findAllByCategoryNameIn(List<String> categories);
}
