package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAll();
}
