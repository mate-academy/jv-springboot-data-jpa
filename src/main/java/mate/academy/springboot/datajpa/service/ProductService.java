package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {

    Product save(Product product);

    Product getById(Long id);

    Product deleteById(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(int from, int to);

    List<Product> getAllByCategoriesIn(List<Long> categoriesIds);

}
