package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;

import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product toModel);

    Product getById(Long id) throws ServiceDataException;

    void deleteById(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategory(Long categoryId) throws ServiceDataException;
}
