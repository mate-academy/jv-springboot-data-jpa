package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> findAll();
}
