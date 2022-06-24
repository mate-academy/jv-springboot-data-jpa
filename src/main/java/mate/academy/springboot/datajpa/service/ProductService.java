package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product toModel);

    Product getById(Long id) throws ServiceDataException;

    void deleteById(Long id);

    Product update(Product product);

    List<Product> findAll(Map<String, String> params);
}
