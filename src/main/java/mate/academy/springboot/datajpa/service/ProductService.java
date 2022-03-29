package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends AbstractService<Product> {

    List<Product> findAll(Map<String, String> parameters);
}
