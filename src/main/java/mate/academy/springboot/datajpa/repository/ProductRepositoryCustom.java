package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Product;
import java.util.List;
import java.util.Map;

public interface ProductRepositoryCustom {
    List<Product> findAll(Map<String, String> params);
}
