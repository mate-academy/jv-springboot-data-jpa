package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService extends AbstractService<Product>{

    List<Product> findAll(Map<String, String> parameters);
}
