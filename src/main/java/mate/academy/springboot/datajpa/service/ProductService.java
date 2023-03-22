package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product entity);

    Product get(Long id);

    void delete(Long id);

    List<Product> findAll(Map<String, String> params);

}
