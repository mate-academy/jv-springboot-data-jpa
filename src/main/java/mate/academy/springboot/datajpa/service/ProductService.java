package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product update(Product product);

    void delete(Product product);

    Product getById(Long id);

    List<Product> getAll(Map<String, String> params);
}
