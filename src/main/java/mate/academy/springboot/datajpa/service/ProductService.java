package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAll(Map<String, String> params);
}
