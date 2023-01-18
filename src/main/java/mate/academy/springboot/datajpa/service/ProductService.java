package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product get(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> getAll(Map<String, String> params);
}
