package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    List<Product> getByPriceBetween(Integer lowerPrice, Integer higherPrice);

    List<Product> getByCategories(List<Category> categories);
}
