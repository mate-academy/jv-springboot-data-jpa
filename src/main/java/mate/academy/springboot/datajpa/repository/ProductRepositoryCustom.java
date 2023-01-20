package mate.academy.springboot.datajpa.repository;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductRepositoryCustom {
    List<Product> findAll(Map<String, String> params);
}
