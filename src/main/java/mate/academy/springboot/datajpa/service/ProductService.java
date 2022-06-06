package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product toModel);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Product product);
}
