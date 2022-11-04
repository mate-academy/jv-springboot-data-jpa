package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
    void delete(Long id);
    Product update(Product product);
}
