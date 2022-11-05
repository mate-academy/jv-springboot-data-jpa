package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Product product);

    Product update(Product product);
}
