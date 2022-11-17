package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product getById(Long id);

    Product deleteById(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(int from, int to);

    List<Product> getAllByCategoriesIn(List<Long> categoriesIds);


}
